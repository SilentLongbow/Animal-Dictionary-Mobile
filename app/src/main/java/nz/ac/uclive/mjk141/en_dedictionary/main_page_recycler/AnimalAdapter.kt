package nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal
import nz.ac.uclive.mjk141.en_dedictionary.databinding.ListItemAnimalBinding

class AnimalAdapter(val clickListener: AnimalListener) : ListAdapter<Animal, AnimalAdapter.ViewHolder>(AnimalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Parent - RecyclerView that ViewHolder will be in, and context is the activity RV is in.
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, clickListener)
    }
    /* ViewHolder represents a single row in the list. One instance of the ViewHolder
            contains one instance of row layout and metadata about row. */
    class ViewHolder private constructor(private val binding: ListItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: Animal, clickListener: AnimalListener) {
            binding.animal = currentItem
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAnimalBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AnimalDiffCallback : DiffUtil.ItemCallback<Animal>() {
    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem == newItem
    }

}

class AnimalListener(val clickListener: (animalId: Long) -> Unit) {
    fun onClick(animal: Animal) = clickListener(animal.id)
}