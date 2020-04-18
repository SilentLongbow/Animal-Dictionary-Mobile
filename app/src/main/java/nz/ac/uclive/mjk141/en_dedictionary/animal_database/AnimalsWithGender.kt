package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import androidx.room.Embedded
import androidx.room.Relation

data class AnimalsWithGender(
    @Embedded val grammaticalGender: GrammaticalGender,
    @Relation(
        parentColumn = "genderName",
        entityColumn = "animalGenderName"
    )
    val animals: List<Animal>
)