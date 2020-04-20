package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.content.Intent
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

    private val _imageSelected = MutableLiveData<Boolean>()
    val imageSelected: LiveData<Boolean>
        get() = _imageSelected

    init {
        _imageSelected.value = false
        _eventSelectingImage.value = false
    }


    // Event functions
    fun eventImageSelected() {
        _imageSelected.value = true
    }

    fun eventImageDeselected() {
        _imageSelected.value = false
    }

    fun eventSelectingImageStart() {
        _eventSelectingImage.value = true
    }

    fun eventSelectingImageCompleted() {
        _eventSelectingImage.value = false
    }
}
