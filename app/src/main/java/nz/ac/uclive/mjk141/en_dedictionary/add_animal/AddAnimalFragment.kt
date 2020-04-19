package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.add_animal_fragment.view.*
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.databinding.AddAnimalFragmentBinding


class AddAnimalFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddAnimalFragment()
    }

    private lateinit var viewModel: AddAnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AddAnimalFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_animal_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val animalDao = AnimalDatabase.getInstance(application).animalDao
        val viewModelFactory = AddAnimalViewModelFactory(animalDao)

        viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(AddAnimalViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.newAnimalViewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
