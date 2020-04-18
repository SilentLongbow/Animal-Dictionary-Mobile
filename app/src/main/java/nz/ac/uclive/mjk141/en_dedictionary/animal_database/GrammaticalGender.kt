package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "grammatical_gender_table")
data class GrammaticalGender(
    @PrimaryKey
    @ColumnInfo(name = "gender_name")
    val genderName: String,

    @ColumnInfo(name = "gender_text")
    val genderText: String) {

    override fun equals(other: Any?)
    = (other is GrammaticalGender)
            && genderName == other.genderName
            && genderText == other.genderText

    override fun hashCode(): Int {
        return genderName.hashCode()
    }
}