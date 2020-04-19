package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao

class AddAnimalViewModelFactory(
    private val animalDao: AnimalDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAnimalViewModel::class.java)) {
            return AddAnimalViewModel(animalDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}