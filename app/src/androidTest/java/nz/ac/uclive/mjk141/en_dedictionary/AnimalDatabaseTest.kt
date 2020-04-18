package nz.ac.uclive.mjk141.en_dedictionary

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabaseDao
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.GrammaticalGender
import nz.ac.uclive.mjk141.en_dedictionary.utils.Gender
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class AnimalDatabaseTest {

    private lateinit var animalDao: AnimalDatabaseDao
    private lateinit var db: AnimalDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AnimalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        animalDao = db.animalDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetGender() {
        val gender = GrammaticalGender(Gender.FEMININE.name, Gender.FEMININE.str)
        animalDao.insert(gender)
        val newGender = animalDao.getGender(Gender.FEMININE.name)
        Assert.assertEquals(gender, newGender)
    }
}