package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import nz.ac.uclive.mjk141.en_dedictionary.utils.Gender
import java.io.File
import java.io.FileOutputStream
import java.util.*

class AddAnimalViewModel(
    private val database: AnimalDao
) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _englishName = MutableLiveData<String>()
    val englishName: LiveData<String>
        get() = _englishName

    private val _germanName = MutableLiveData<String>()
    val germanName: LiveData<String>
        get() = _germanName

    private val grammaticalGender = MutableLiveData<Gender>()
    private val imageName = MutableLiveData<String>()

    private val _eventSelectingImage = MutableLiveData<Boolean>()
    val eventSelectingImage: LiveData<Boolean>
        get() = _eventSelectingImage

    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    private val _saveButtonPressed = MutableLiveData<Boolean>()
    val saveButtonPressed: LiveData<Boolean>
        get() = _saveButtonPressed

    private val _englishNameExists = MutableLiveData<Boolean>()
    val englishNameExists: LiveData<Boolean>
        get() = _englishNameExists

    private val _germanNameExists = MutableLiveData<Boolean>()
    val germanNameExists: LiveData<Boolean>
        get() = _germanNameExists

    private val _eventAnimalInserted = MutableLiveData<Boolean>()
    val eventAnimalInserted: LiveData<Boolean>
        get() = _eventAnimalInserted

    init {
        _eventSelectingImage.value = false
        _saveButtonPressed.value = false
        _eventAnimalInserted.value = false
        _germanNameExists.value = false
        _englishNameExists.value = false
        _englishName.value = ""
        _germanName.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
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

    fun setGender(gender: Gender) {
        grammaticalGender.value = gender
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
        methodSelectionIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            arrayOf(gallerySelectionIntent)
        )
        return methodSelectionIntent
    }

    fun updateEnglishName(string: String) {
        _englishName.value = string.trim()
    }

    fun updateGermanName(string: String) {
        _germanName.value = string.trim()
    }

    fun eventSaveStarted() {
        _saveButtonPressed.value = true
    }

    fun eventSaveEnded() {
        _saveButtonPressed.value = false
    }

    fun invalidName(name: String?) = name.isNullOrBlank()

    fun noImage() = _imageBitmap.value == null

    fun saveAnimal(imageDir: File) {
        storeImageToInternal(imageDir)
        val animal = Animal(
            0, _englishName.value!!,
            _germanName.value!!,
            grammaticalGender.value!!,
            imageName.value!!
        )
        uiScope.launch {
            insertAnimalIntoDatabase(animal)
        }
    }

    private suspend fun insertAnimalIntoDatabase(animal: Animal) {
        withContext(Dispatchers.IO) {
            val existingEnglishName = database.selectByEnglishName(animal.englishName)
            val existingGermanName = database.selectByEnglishName(animal.germanName)
            when {
                existingEnglishName != null -> {
                    _englishNameExists.postValue(true)
                }
                existingGermanName != null -> {
                    _germanNameExists.postValue(true)
                }
                else -> {
                    database.insert(animal)
                    _eventAnimalInserted.postValue(true)
                }
            }
        }
    }

    private fun storeImageToInternal(imageDir: File) {
        imageName.value = UUID.randomUUID().toString()
        uiScope.launch {
            val imageFileLocation = File(imageDir, "${imageName.value}.jpg")
            if (!imageFileLocation.exists()) {
                saveBitmapStream(imageFileLocation)
            }
        }
    }

    private suspend fun saveBitmapStream(file: File) {
        withContext(Dispatchers.IO) {
            val outputStream = FileOutputStream(file)
            _imageBitmap.value?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }
    }

    fun clearNamesExist() {
        _englishNameExists.value = false
        _germanNameExists.value = false
    }
}
