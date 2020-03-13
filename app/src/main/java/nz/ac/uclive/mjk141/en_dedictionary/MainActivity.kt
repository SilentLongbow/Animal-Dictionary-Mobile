package nz.ac.uclive.mjk141.en_dedictionary

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            onSearchRequested()
        }

        val dictionaryArray = Translations.entries
        dictionaryView.adapter = DictionaryAdapter(dictionaryArray)
        dictionaryView.layoutManager = LinearLayoutManager(this)
        dictionaryView.setHasFixedSize(true)

    }
}
