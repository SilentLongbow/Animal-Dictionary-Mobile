package nz.ac.uclive.mjk141.en_dedictionary

import android.app.Activity
import android.app.ListActivity
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchableActivity : ListActivity() {
    /* Presents search results in a list, so use list activity */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)
        }
    }

    override fun onSearchRequested(): Boolean {
        return super.onSearchRequested()
    }
}
