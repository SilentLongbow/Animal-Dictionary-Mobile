package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.add_animal_fragment.*
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.databinding.AddAnimalFragmentBinding
import nz.ac.uclive.mjk141.en_dedictionary.utils.Gender
import nz.ac.uclive.mjk141.en_dedictionary.utils.IMAGE_SELECTION_CODE
import nz.ac.uclive.mjk141.en_dedictionary.utils.hideSoftKeyboard
import java.io.File
import java.io.FileDescriptor


class AddAnimalFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() =
            AddAnimalFragment()
    }

    private lateinit var viewModel: AddAnimalViewModel
    private lateinit var binding: AddAnimalFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val animalDao = AnimalDatabase.getInstance(application).animalDao
        val viewModelFactory = AddAnimalViewModelFactory(animalDao)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_animal_fragment, container, false
        )

        viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(AddAnimalViewModel::class.java)

        binding.englishNameEntry.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideSoftKeyboard(view, context)
            }
        }
        binding.germanNameEntry.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideSoftKeyboard(view, context)
            }
        }
        setUpFocusableViews(binding)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newAnimalViewModel = viewModel
        binding.addImageButton.setOnClickListener {
            viewModel.eventSelectingImageStart()
        }

        val imageButtonObserver = Observer<Boolean> { selectingImage ->
            if (selectingImage) {
                pickImage()
            }
        }
        viewModel.eventSelectingImage.observe(viewLifecycleOwner, imageButtonObserver)

        val imageSelectedObserver = Observer<Bitmap> { imageBitmap ->
            if (imageBitmap != null) {
                binding.imageDisplay.setImageBitmap(imageBitmap)
            } else {
                binding.imageDisplay.setImageDrawable(
                    resources.getDrawable(R.drawable.ic_image_black_24dp, context?.theme)
                )
            }
        }
        viewModel.imageBitmap.observe(viewLifecycleOwner, imageSelectedObserver)

        val saveButtonObserver = Observer<Boolean> { buttonPressed ->
            if (buttonPressed) {
                if (validateFields()) {
                    val imageDir = getImageDirectory()
                    viewModel.saveAnimal(imageDir)
                }
                viewModel.eventSaveEnded()
            }
        }
        viewModel.saveButtonPressed.observe(viewLifecycleOwner, saveButtonObserver)

        val englishNameExistsObserver = Observer<Boolean> { nameExists ->
            if (nameExists) {
                val displayText = resources.getString(
                    R.string.already_exists_toast_display,
                    viewModel.englishName.value
                )
                displayToast(displayText, Toast.LENGTH_LONG)
                shakeView(binding.englishNameEntry)
            }
        }
        viewModel.englishNameExists.observe(viewLifecycleOwner, englishNameExistsObserver)

        val germanNameExistsObserver = Observer<Boolean> { nameExists ->
            if (nameExists) {
                val displayText = resources.getString(
                    R.string.already_exists_toast_display,
                    viewModel.germanName.value
                )
                displayToast(displayText, Toast.LENGTH_LONG)
                shakeView(binding.germanNameEntry)
                viewModel.clearNamesExist()
            }
        }
        viewModel.germanNameExists.observe(viewLifecycleOwner, germanNameExistsObserver)

        val nameInsertedObserver = Observer<Boolean> { animalInserted ->
            if (animalInserted) {
                val displayText = resources.getString(R.string.event_animal_added)
                displayToast(displayText, Toast.LENGTH_LONG)
                findNavController().navigate(AddAnimalFragmentDirections.actionFragmentAddAnimalToAnimalSelectionFragment())
            }
        }
        viewModel.eventAnimalInserted.observe(viewLifecycleOwner, nameInsertedObserver)

        configureNameFields()
        spinnerData()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimalViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setUpFocusableViews(binding: AddAnimalFragmentBinding) {
        binding.entryCardView.isFocusable = true
        binding.entryCardView.isFocusableInTouchMode = true
        binding.entryCardView.isClickable = true

        binding.backgroundLayout.isFocusable = true
        binding.backgroundLayout.isFocusableInTouchMode = true
        binding.backgroundLayout.isClickable = true
    }

    private fun configureNameFields() {
        binding.englishNameEntry.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.updateEnglishName(englishNameEntry.text.toString())
            }
        })

        binding.germanNameEntry.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.updateGermanName(germanNameEntry.text.toString())
            }
        })

    }

    private fun pickImage() {
        val methodSelectionIntent = viewModel.createImageSelectionIntents()
        startActivityForResult(methodSelectionIntent, IMAGE_SELECTION_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_SELECTION_CODE) {
            val imageUri = data?.data
            val imageBitmap = getBitmapFromUri(imageUri!!)
            viewModel.storeImage(imageBitmap)
        }
        viewModel.eventSelectingImageCompleted()
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            context?.contentResolver?.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return image
    }

    private fun spinnerData() {

        binding.genderSelectionSpinner.setOnTouchListener { view, _ ->
            hideSoftKeyboard(view, context)
            false
        }
        val spinnerArrayAdapter: ArrayAdapter<Gender> =
            ArrayAdapter<Gender>(context!!, android.R.layout.simple_spinner_item, Gender.values())
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSelectionSpinner.adapter = spinnerArrayAdapter
        binding.genderSelectionSpinner.onItemSelectedListener = this
    }

    private fun validateFields(): Boolean {
        val englishName = binding.englishNameEntry.text.toString()
        val germanName = binding.germanNameEntry.text.toString()
        var allFieldsValid = true
        if (viewModel.invalidName(englishName)) {
            shakeView(englishNameEntry)
            allFieldsValid = false
        }
        if (viewModel.invalidName(germanName)) {
            shakeView(germanNameEntry)
            allFieldsValid = false
        }
        if (viewModel.noImage()) {
            shakeView(binding.imageDisplay)
            allFieldsValid = false
        }
        return allFieldsValid
    }

    private fun getImageDirectory(): File {
        val contextWrapper = ContextWrapper(context)
        return contextWrapper.getDir("images", Context.MODE_PRIVATE)
    }

    private fun shakeView(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        view.startAnimation(animation)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedGender: Gender = parent?.getItemAtPosition(position) as Gender
        viewModel.setGender(selectedGender)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun displayToast(text: String, duration: Int) {
        Toast.makeText(context, text, duration).show()
    }
}
