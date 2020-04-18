package nz.ac.uclive.mjk141.en_dedictionary.add_animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.R


class FragmentAddAnimal : Fragment() {

    companion object {
        fun newInstance() =
            FragmentAddAnimal()
    }

    private lateinit var viewModel: AddAnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_animal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
