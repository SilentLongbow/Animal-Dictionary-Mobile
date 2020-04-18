package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal_table")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val animalId: Long = 0L,

    @ColumnInfo(name = "english_name")
    val englishName: String,

    @ColumnInfo(name = "german_name")
    val germanName: String,

    @ColumnInfo(name = "animal_gender_name")
    val animalGenderName: String,

    @ColumnInfo(name = "image_id")
    val imageId: String
)