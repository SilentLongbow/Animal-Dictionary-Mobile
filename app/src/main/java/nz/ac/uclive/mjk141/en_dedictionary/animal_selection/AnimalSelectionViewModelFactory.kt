package nz.ac.uclive.mjk141.en_dedictionary.animal_selection

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao

class AnimalSelectionViewModelFactory(
    private val dataSource: AnimalDao,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalSelectionViewModel::class.java)) {
            return AnimalSelectionViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}