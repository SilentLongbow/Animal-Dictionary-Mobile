package nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal

@Dao
interface AnimalDao: BaseDao<Animal> {

    @Query("SELECT * FROM animal_table WHERE id = :key")
    fun selectById(key: Long): LiveData<Animal>

    @Query("SELECT * FROM animal_table")
    fun selectAll(): LiveData<List<Animal>>

    @Query("SELECT * FROM animal_table ORDER BY :name ASC")
    fun selectAllAndSortBy(name: String): List<Animal>

    @Query("SELECT * FROM animal_table WHERE english_name = :englishName")
    fun selectByEnglishName(englishName: String): Animal?

    @Query("SELECT * FROM animal_table WHERE german_name = :germanName")
    fun selectByGermanName(germanName: String): Animal?
}