package nz.ac.uclive.mjk141.en_dedictionary.animal_selection

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import java.io.File
import java.util.*

class AnimalSelectionViewModel(
    val database: AnimalDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var animals = database.selectAll()

    private val _navigateToAnimal = MutableLiveData<Long>()
    val navigateToAnimal: LiveData<Long>
        get() = _navigateToAnimal

    fun onAnimalClicked(id: Long) {
        _navigateToAnimal.value = id
    }

    fun onAnimalNavigated() {
        _navigateToAnimal.value = null
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}