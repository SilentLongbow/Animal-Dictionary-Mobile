package nz.ac.uclive.mjk141.en_dedictionary.view_animal

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.AnimalDatabase
import nz.ac.uclive.mjk141.en_dedictionary.databinding.ViewAnimalFragmentBinding
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ViewAnimalFragment : Fragment() {

    private lateinit var binding: ViewAnimalFragmentBinding
    private lateinit var viewModel: ViewAnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.view_animal_fragment, container, false
        )

        val application = requireNotNull(activity).application
        val arguments = ViewAnimalFragmentArgs.fromBundle(arguments!!)

        val dataSource = AnimalDatabase.getInstance(application).animalDao
        val viewModelFactory = ViewAnimalViewModelFactory(arguments.animalId, dataSource)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ViewAnimalViewModel::class.java)

        binding.animalViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getAnimal().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setAnimalTitle()
                val imageDir = getImageDir()
                viewModel.loadAnimalImage(imageDir)
            }
        })

        viewModel.imageBitmap.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.animalImage.setImageBitmap(it)
            }
        })

        return binding.root
    }

    private fun setAnimalTitle() {
        if (Locale.getDefault().displayLanguage.toLowerCase(Locale.ROOT).contains("de")) {
            val germanTitle = "${viewModel.getAnimal().value?.gender.toString()} ${viewModel.getAnimal().value?.germanName}"
            (activity as AppCompatActivity).supportActionBar?.title = germanTitle

        } else {
            val englishTitle = viewModel.getAnimal().value?.englishName
            (activity as AppCompatActivity).supportActionBar?.title = englishTitle
        }
    }

    private fun getImageDir(): File {
        val contextWrapper = ContextWrapper(context)
        return contextWrapper.getDir("images", Context.MODE_PRIVATE)
    }

}
