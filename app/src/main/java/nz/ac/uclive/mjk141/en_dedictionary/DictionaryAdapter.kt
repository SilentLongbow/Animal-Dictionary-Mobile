package nz.ac.uclive.mjk141.en_dedictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dictionary_item.view.*

class DictionaryAdapter(private val dictionaryArray: Array<DictionaryEntry>) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        // Parent - RecyclerView that ViewHolder will be in, and context is the activity RV is in.
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item,
        parent, false)

        return DictionaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val currentItem = dictionaryArray[position]

        holder.dictionaryImageView.setImageResource(R.drawable.placeholder_animal)
        holder.dictionaryTextView.text = currentItem.term
    }

    override fun getItemCount() = dictionaryArray.size

    /* ViewHolder represents a single row in the list. One instance of the ViewHolder
        contains one instance of row layout and metadata about row. */
    class DictionaryViewHolder(itemViewer: View) : RecyclerView.ViewHolder(itemViewer) {
        val dictionaryImageView: ImageView = itemView.dictionaryItemImage
        val dictionaryTextView: TextView = itemView.dictionaryItemText
    }
}