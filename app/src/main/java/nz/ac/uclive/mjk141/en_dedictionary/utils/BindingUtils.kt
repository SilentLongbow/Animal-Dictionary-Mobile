package nz.ac.uclive.mjk141.en_dedictionary.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import nz.ac.uclive.mjk141.en_dedictionary.R
import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal

@BindingAdapter("germanNameFormatted")
fun TextView.setGermanNameFormatted(animal: Animal?) {
    animal?.let {
        text = context.resources.getString(R.string.german_text_with_gender,
            animal.gender.toString(),
            animal.germanName)
    }
}

@BindingAdapter("animalImage")
fun ImageView.setAnimalImage(animal: Animal) {
    if (animal.imageBitmap == null) {
        setImageResource(R.drawable.ic_image_black_24dp)
    } else {
        setImageBitmap(animal.imageBitmap)
    }
}