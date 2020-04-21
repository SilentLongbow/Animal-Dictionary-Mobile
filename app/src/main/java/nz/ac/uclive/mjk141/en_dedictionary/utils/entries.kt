package nz.ac.uclive.mjk141.en_dedictionary.utils

import nz.ac.uclive.mjk141.en_dedictionary.animal_database.Animal


enum class Gender(private val str: String) {
    Masculine("der"),
    Feminine("die"),
    Neutral("das");

    override fun toString() = str
}

object Translations {
    val entries = arrayListOf(
        Animal(
            englishName = "Alligator",
            germanName = "Alligator",
            gender = Gender.Masculine,
            imageId = "Alligator.jpg"
        ),
        Animal(
            englishName = "Bear",
            germanName = "Bär",
            gender = Gender.Masculine,
            imageId = "Bear.jpg"
        ),
        Animal(
            englishName = "Butterfly",
            germanName = "Schmetterling",
            gender = Gender.Masculine,
            imageId = "Butterfly.jpg"
        ),
        Animal(
            englishName = "Camel",
            germanName = "Kamel",
            gender = Gender.Neutral,
            imageId = "Camel.jpg"
        ),
        Animal(
            englishName = "Cat",
            germanName = "Katze",
            gender = Gender.Feminine,
            imageId = "Cat.jpg"
        ),
        Animal(
            englishName = "Caterpillar",
            germanName = "Raupe",
            gender = Gender.Feminine,
            imageId = "Caterpillar.jpg"
        ),
        Animal(
            englishName = "Cheetah",
            germanName = "Gepard",
            gender = Gender.Masculine,
            imageId = "Cheetah.jpg"
        ),
        Animal(
            englishName = "Cow",
            germanName = "Kuh",
            gender = Gender.Feminine,
            imageId = "Cow.jpg"
        ),
        Animal(
            englishName = "Deer",
            germanName = "Reh",
            gender = Gender.Neutral,
            imageId = "Deer.jpg"
        ),
        Animal(
            englishName = "Dog",
            germanName = "Hund",
            gender = Gender.Masculine,
            imageId = "Dog.jpg"
        ),
        Animal(
            englishName = "Donkey",
            germanName = "Esel",
            gender = Gender.Neutral,
            imageId = "Donkey.jpg"
        ),
        Animal(
            englishName = "Eagle",
            germanName = "Adler",
            gender = Gender.Masculine,
            imageId = "Eagle.jpg"
        ),
        Animal(
            englishName = "Elephant",
            germanName = "Elefant",
            gender = Gender.Masculine,
            imageId = "Elephant.jpg"
        ),
        Animal(
            englishName = "Fish",
            germanName = "Fisch",
            gender = Gender.Masculine,
            imageId = "Fish.jpg"
        ),
        Animal(
            englishName = "Fox",
            germanName = "Fuchs",
            gender = Gender.Masculine,
            imageId = "Fox.jpg"
        ),
        Animal(
            englishName = "Giraffe",
            germanName = "Giraffe",
            gender = Gender.Feminine,
            imageId = "Giraffe.jpg"
        ),
        Animal(
            englishName = "Goat",
            germanName = "Ziege",
            gender = Gender.Feminine,
            imageId = "Goat.jpg"
        ),
        Animal(
            englishName = "Hedgehog",
            germanName = "Igel",
            gender = Gender.Masculine,
            imageId = "Hedgehog.jpg"
        ),
        Animal(
            englishName = "Hippopotamus",
            germanName = "Nilpferd",
            gender = Gender.Neutral,
            imageId = "Hippopotamus.jpg"
        ),
        Animal(
            englishName = "Horse",
            germanName = "Pferd",
            gender = Gender.Neutral,
            imageId = "Horse.jpg"
        ),
        Animal(
            englishName = "Kangaroo",
            germanName = "Känguru",
            gender = Gender.Neutral,
            imageId = "Kangaroo.jpg"
        ),
        Animal(
            englishName = "Kiwi",
            germanName = "Kiwi",
            gender = Gender.Feminine,
            imageId = "Kiwi.jpg"
        ),
        Animal(
            englishName = "Lion",
            germanName = "Löwe",
            gender = Gender.Masculine,
            imageId = "Lion.jpg"
        ),
        Animal(
            englishName = "Lynx",
            germanName = "Luchs",
            gender = Gender.Masculine,
            imageId = "Lynx.jpg"
        ),
        Animal(
            englishName = "Monkey",
            germanName = "Affe",
            gender = Gender.Masculine,
            imageId = "Monkey.jpg"
        ),
        Animal(
            englishName = "Octopus",
            germanName = "Oktopus",
            gender = Gender.Masculine,
            imageId = "Octopus.jpg"
        ),
        Animal(
            englishName = "Ostrich",
            germanName = "Strauß",
            gender = Gender.Masculine,
            imageId = "Ostrich.jpg"
        ),
        Animal(
            englishName = "Parrot",
            germanName = "Papagei",
            gender = Gender.Masculine,
            imageId = "Parrot.jpg"
        ),
        Animal(
            englishName = "Pig",
            germanName = "Schwein",
            gender = Gender.Neutral,
            imageId = "Pig.jpg"
        ),
        Animal(
            englishName = "Rabbit",
            germanName = "Kaninchen",
            gender = Gender.Neutral,
            imageId = "Rabbit.jpg"
        ),
        Animal(
            englishName = "Rhinoceros",
            germanName = "Nashorn",
            gender = Gender.Neutral,
            imageId = "Rhino.jpg"
        ),
        Animal(
            englishName = "Seal",
            germanName = "Siegel",
            gender = Gender.Neutral,
            imageId = "Seal.jpg"
        ),
        Animal(
            englishName = "Snake",
            germanName = "Schlange",
            gender = Gender.Feminine,
            imageId = "Snake.jpg"
        ),
        Animal(
            englishName = "Tiger",
            germanName = "Tiger",
            gender = Gender.Masculine,
            imageId = "Tiger.jpg"
        ),
        Animal(
            englishName = "Tortoise",
            germanName = "Schildkröte",
            gender = Gender.Feminine,
            imageId = "Tortoise.jpg"
        ),
        Animal(
            englishName = "Weasel",
            germanName = "Wiesel",
            gender = Gender.Masculine,
            imageId = "Weasel.jpg"
        ),
        Animal(
            englishName = "Wolf",
            germanName = "Wolf",
            gender = Gender.Masculine,
            imageId = "Wolf.jpg"
        )
    )
}