package nz.ac.uclive.mjk141.en_dedictionary

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AnimalDatabaseTest {

    private lateinit var animalDao: AnimalDao
    private lateinit var db: AnimalDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AnimalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        animalDao = db.animalDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}