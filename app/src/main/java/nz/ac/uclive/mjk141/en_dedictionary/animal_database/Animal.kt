package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import nz.ac.uclive.mjk141.en_dedictionary.utils.Gender

@Entity(
    tableName = "animal_table",
    indices = [Index(value = ["english_name"], unique = true),
        Index(value = ["german_name"], unique = true)]
)
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "english_name")
    val englishName: String,

    @ColumnInfo(name = "german_name")
    val germanName: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "image_id")
    val imageId: String
)