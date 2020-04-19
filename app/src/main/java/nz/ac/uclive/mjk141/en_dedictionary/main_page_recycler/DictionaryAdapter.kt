package nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dictionary_item.view.*
import nz.ac.uclive.mjk141.en_dedictionary.R

class DictionaryAdapter(private val dictionary: ArrayList<DictionaryEntry>) :
    RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>(), Filterable {
    private var fullDictionary: ArrayList<DictionaryEntry> = dictionary.clone() as ArrayList<DictionaryEntry>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        // Parent - RecyclerView that ViewHolder will be in, and context is the activity RV is in.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.dictionary_item,
        parent, false)
        return DictionaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val currentItem = dictionary[position]

        holder.dictionaryImageView.setImageResource(R.drawable.placeholder_animal)
        holder.dictionaryTextView.text = currentItem.term
    }

    override fun getItemCount() = dictionary.size

    /* ViewHolder represents a single row in the list. One instance of the ViewHolder
        contains one instance of row layout and metadata about row. */
    class DictionaryViewHolder(itemViewer: View) : RecyclerView.ViewHolder(itemViewer) {
        val dictionaryImageView: ImageView = itemView.dictionaryItemImage
        val dictionaryTextView: TextView = itemView.dictionaryItemText
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

//    private val dictionaryFilter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            val filteredEntries: ArrayList<DictionaryEntry> = arrayListOf()
//            if (constraint == null || constraint.isEmpty()) {
//                filteredEntries.addAll(fullDictionary)
//            } else {
//                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
//
//            }
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            TODO("Not yet implemented")
//        }
//    }
}