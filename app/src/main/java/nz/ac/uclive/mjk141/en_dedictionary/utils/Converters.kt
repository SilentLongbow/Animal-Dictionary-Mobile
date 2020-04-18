package nz.ac.uclive.mjk141.en_dedictionary.utils

import androidx.room.TypeConverter

class Converters {

    /*
     * Takes a Gender enum type and converts it to a char representation.
     */
    @TypeConverter
    fun fromGenderToChar(gender: Gender?): Char? {
        return gender?.let {
            when (it) {
                Gender.Feminine -> 'F'
                Gender.Masculine -> 'M'
                Gender.Neutral -> 'N'
            }
        }
    }

    /*
     * Takes the char representation of a Gender and returns its corresponding enum type.
     */
    @TypeConverter
    fun fromCharToGender(value: Char?): Gender? {
        return value?.let {
            when (it) {
                'F' -> Gender.Feminine
                'M' -> Gender.Masculine
                'N' -> Gender.Neutral
                else -> null
            }
        }
    }
}