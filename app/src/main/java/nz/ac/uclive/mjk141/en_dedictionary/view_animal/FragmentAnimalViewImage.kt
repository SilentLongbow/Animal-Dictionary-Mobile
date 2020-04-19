package nz.ac.uclive.mjk141.en_dedictionary.view_animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nz.ac.uclive.mjk141.en_dedictionary.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentAnimalViewImage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_image_view, container, false)
    }

}
