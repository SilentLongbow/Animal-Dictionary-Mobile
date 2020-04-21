package nz.ac.uclive.mjk141.en_dedictionary.animal_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import nz.ac.uclive.mjk141.en_dedictionary.utils.Converters

@Database(entities = [Animal::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimalDatabase : RoomDatabase() {

    abstract val animalDao: AnimalDao

    companion object {

        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalDatabase::class.java,
                        "en_de_dictionary.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}