package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface AnimalDatabaseDao {

    @Insert
    fun insert(animal: Animal)

    @Insert
    fun insert(gender: GrammaticalGender)

    @Transaction
    @Query("SELECT * FROM animal_table")
    fun getAnimalWithGender(): Animal?

    @Query("SELECT * FROM grammatical_gender_table WHERE gender_name = :name")
    fun getGender(name: String): GrammaticalGender?
}