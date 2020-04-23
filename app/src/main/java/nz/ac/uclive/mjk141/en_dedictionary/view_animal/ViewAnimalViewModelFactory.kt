package nz.ac.uclive.mjk141.en_dedictionary.view_animal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao

class ViewAnimalViewModelFactory(
    private val animalId: Long,
    private val dataSource: AnimalDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewAnimalViewModel::class.java)) {
            return ViewAnimalViewModel(animalId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}