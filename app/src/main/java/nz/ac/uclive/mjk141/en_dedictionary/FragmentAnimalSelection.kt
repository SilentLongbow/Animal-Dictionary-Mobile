package nz.ac.uclive.mjk141.en_dedictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_animal_selection.*
import kotlinx.android.synthetic.main.fragment_animal_selection.view.*

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
