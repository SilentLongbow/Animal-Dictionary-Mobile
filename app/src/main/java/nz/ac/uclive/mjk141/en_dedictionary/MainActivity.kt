package nz.ac.uclive.mjk141.en_dedictionary

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler.DictionaryAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val dictionaryArray = Translations.entries
        dictionaryView.adapter =
            DictionaryAdapter(
                dictionaryArray
            )
        dictionaryView.layoutManager = LinearLayoutManager(this)
        dictionaryView.setHasFixedSize(true)

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
}
