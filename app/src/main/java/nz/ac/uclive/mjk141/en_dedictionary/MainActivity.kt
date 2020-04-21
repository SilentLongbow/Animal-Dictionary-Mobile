package nz.ac.uclive.mjk141.en_dedictionary

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.dao.AnimalDao
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var prefs: SharedPreferences

    private lateinit var animalDatabase: AnimalDatabase
    private lateinit var animalDao: AnimalDao
    private val mainJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(custom_toolbar)

        navController = this.findNavController(R.id.navigationFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        animalDatabase = AnimalDatabase.getInstance(applicationContext)
        animalDao = animalDatabase.animalDao

        prefs = getSharedPreferences("nz.ac.uclive.mjk141.en_dedictonary", Context.MODE_PRIVATE)

        //val assetList = assets.list("animal_images/")

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()

        if (prefs.getBoolean("first_run", true)) {
            populateDatabase()
            prefs.edit().putBoolean("first_run", false).apply()
        }

    }

    private fun populateDatabase() {

        uiScope.launch {

        }
    }
}
