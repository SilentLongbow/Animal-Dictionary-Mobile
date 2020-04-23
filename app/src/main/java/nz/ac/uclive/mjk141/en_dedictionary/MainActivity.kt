package nz.ac.uclive.mjk141.en_dedictionary

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(custom_toolbar)

        navController = this.findNavController(R.id.navigationFragment)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        prefs = getSharedPreferences("nz.ac.uclive.mjk141.en_dedictonary", Context.MODE_PRIVATE)

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
    }
}
