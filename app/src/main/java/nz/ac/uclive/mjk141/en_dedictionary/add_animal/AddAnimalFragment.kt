package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.databinding.AddAnimalFragmentBinding
import nz.ac.uclive.mjk141.en_dedictionary.utils.IMAGE_SELECTION_CODE
import nz.ac.uclive.mjk141.en_dedictionary.utils.hideSoftKeyboard


class AddAnimalFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddAnimalFragment()
    }

    private lateinit var viewModel: AddAnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AddAnimalFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_animal_fragment, container, false
        )

        val application = requireNotNull(this.activity).application
        val animalDao = AnimalDatabase.getInstance(application).animalDao
        val viewModelFactory = AddAnimalViewModelFactory(animalDao)

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

        binding.backgroundLayout?.isFocusable = true
        binding.backgroundLayout?.isFocusableInTouchMode = true
        binding.backgroundLayout?.isClickable = true

        binding.leftSideConstraintLayout?.isFocusable = true
        binding.leftSideConstraintLayout?.isFocusableInTouchMode = true
        binding.leftSideConstraintLayout?.isClickable = true
    }


    private fun pickImage() {
        val documentsSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        documentsSelectionIntent.type = "image/*"

        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        galleryIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(documentsSelectionIntent, "Select Image")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(galleryIntent))

        startActivityForResult(chooserIntent, IMAGE_SELECTION_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_SELECTION_CODE) {
            val imageUri = data?.data
            // TODO Move some logic to viewModel (creation of intent) and launch intent here.
        }
    }
}
