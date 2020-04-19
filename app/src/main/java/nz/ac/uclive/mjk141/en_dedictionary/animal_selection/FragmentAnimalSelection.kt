package nz.ac.uclive.mjk141.en_dedictionary.animal_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_animal_selection.view.*
import nz.ac.uclive.mjk141.en_dedictionary.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentAnimalSelection : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout: View = inflater.inflate(R.layout.fragment_animal_selection, container, false)

        layout.the_button.setOnClickListener {
            findNavController().navigate(FragmentAnimalSelectionDirections.actionAnimalSelectionFragmentToFragmentAnimalViewImage())
        }

        return layout
    }

}
