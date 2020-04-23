package nz.ac.uclive.mjk141.en_dedictionary.animal_selection

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.animal_selection_fragment.*
import kotlinx.android.synthetic.main.animal_selection_fragment.view.*
import nz.ac.uclive.mjk141.en_dedictionary.MainActivity
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler.AnimalAdapter
import nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler.AnimalListener
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AnimalSelectionFragment : Fragment() {

    private lateinit var viewModel: AnimalSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout: View = inflater.inflate(R.layout.animal_selection_fragment, container, false)

        layout.floatingAnimalAddButton.setOnClickListener {
            findNavController().navigate(AnimalSelectionFragmentDirections.navigateToAddAnimaScreen())
        }

        val application = requireNotNull(this.activity).application
        val dataSource = AnimalDatabase.getInstance(application).animalDao

        val viewModelFactory =
            AnimalSelectionViewModelFactory(
                dataSource,
                application
            )
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AnimalSelectionViewModel::class.java)

        val adapter = AnimalAdapter(AnimalListener {
            animalId -> viewModel.onAnimalClicked(animalId)
        })
        layout.animalDisplayRecycler.adapter = adapter

        viewModel.animals.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToAnimal.observe(viewLifecycleOwner, Observer { animalId ->
            animalId?.let {
                findNavController().navigate(
                    AnimalSelectionFragmentDirections.actionAnimalSelectionFragmentToFragmentAnimalView(animalId))
                viewModel.onAnimalNavigated()
            }
        })

        return layout
    }
}
