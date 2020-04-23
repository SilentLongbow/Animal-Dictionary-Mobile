package nz.ac.uclive.mjk141.en_dedictionary.view_animal

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import java.io.File
import java.util.*

class ViewAnimalViewModel(
    private val animalId: Long = 0L,
    dataSource: AnimalDao
) : ViewModel() {

    val database = dataSource

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val animal = MediatorLiveData<Animal>()

    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    fun getAnimal() = animal

    init {
        animal.addSource(database.selectById(animalId), animal::setValue)
    }

    fun loadAnimalImage(imageDir: File) {
        uiScope.launch {
            val fileId = animal.value?.imageId
            loadBitmap(imageDir, fileId)
        }
    }

    private suspend fun loadBitmap(imageDir: File, fileId: String?) {
        withContext(Dispatchers.IO) {
            imageDir.walkTopDown().forEach { file ->
                if (file.isFile && file.nameWithoutExtension == fileId) {
                    _imageBitmap.postValue(BitmapFactory.decodeFile(file.path))
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}