package nz.ac.uclive.mjk141.en_dedictionary

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Activity() {
    private lateinit var dictionaryRecycler: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val startingWords =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        //viewAdapter = MyA
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            onSearchRequested()
        }

    }
}
