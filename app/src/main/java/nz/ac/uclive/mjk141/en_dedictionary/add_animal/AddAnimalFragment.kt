package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
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
import java.io.FileDescriptor


class AddAnimalFragment : Fragment() {

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
                binding.addImageButton.visibility = View.GONE
                binding.imageDisplay.visibility = View.VISIBLE
                binding.imageDisplay.setImageBitmap(imageBitmap)
            } else {
                binding.addImageButton.visibility = View.VISIBLE
                binding.imageDisplay.visibility = View.GONE
            }
        }
        viewModel.imageBitmap.observe(viewLifecycleOwner, imageSelectedObserver)



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
        val parcelFileDescriptor: ParcelFileDescriptor? = context?.contentResolver?.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return image
    }
}
