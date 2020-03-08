package nz.ac.uclive.mjk141.en_dedictionary

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            print("THIS IS A TEST===================")
            onSearchRequested()
        }

    }
}
