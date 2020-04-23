package nz.ac.uclive.mjk141.en_dedictionary.animal_selection

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.animal_selection_fragment.view.*
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase

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

        val adapter =
            AnimalAdapter(
                AnimalListener { animalId ->
                    viewModel.onAnimalClicked(animalId)
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
