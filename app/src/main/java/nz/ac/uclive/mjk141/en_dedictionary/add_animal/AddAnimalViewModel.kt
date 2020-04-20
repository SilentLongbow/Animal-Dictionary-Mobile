package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import nz.ac.uclive.mjk141.en_dedictionary.utils.Gender

class AddAnimalViewModel(
    val database: AnimalDao
) : ViewModel() {

    private val _englishName = MutableLiveData<String>()
    val englishName: LiveData<String>
        get() = _englishName

    private val _germanName = MutableLiveData<String>()
    val germanName: LiveData<String>
        get() = _germanName

    private val _grammaticalGender = MutableLiveData<Gender>()
    val grammaticalGender: LiveData<Gender>
        get() = _grammaticalGender

    private val _imageName = MutableLiveData<String>()
    val imageName: LiveData<String>
        get() = _imageName

    private val _eventSelectingImage = MutableLiveData<Boolean>()
    val eventSelectingImage: LiveData<Boolean>
        get() = _eventSelectingImage

    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    init {
        _eventSelectingImage.value = false
    }

    fun eventSelectingImageStart() {
        _eventSelectingImage.value = true
    }

    fun eventSelectingImageCompleted() {
        _eventSelectingImage.value = false
    }

    fun storeImage(bitmap: Bitmap) {
        _imageBitmap.value = bitmap
    }

    fun createImageSelectionIntents(): Intent {
        val documentsSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        documentsSelectionIntent.type = "image/*"

        val gallerySelectionIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        gallerySelectionIntent.type = "image/*"

        val methodSelectionIntent = Intent.createChooser(documentsSelectionIntent, "Select Image")
        methodSelectionIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(gallerySelectionIntent))
        return methodSelectionIntent
    }
}
