package nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao

import androidx.room.Dao
import androidx.room.Query
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal

@Dao
interface AnimalDao: BaseDao<Animal> {

    @Query("SELECT * FROM animal_table WHERE id = :key")
    fun selectById(key: Long): Animal

    @Query("SELECT * FROM animal_table")
    fun selectAll(): List<Animal?>
}