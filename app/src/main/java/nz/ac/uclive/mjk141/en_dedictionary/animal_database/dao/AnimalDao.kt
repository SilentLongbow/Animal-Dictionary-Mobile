package nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal

@Dao
interface AnimalDao: BaseDao<Animal> {

    @Transaction
    @Query("SELECT * FROM animal_table")
    fun selectAll(): List<Animal?>
}