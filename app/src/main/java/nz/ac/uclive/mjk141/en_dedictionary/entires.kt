package nz.ac.uclive.mjk141.en_dedictionary

import nz.ac.uclive.mjk141.en_dedictionary.main_page_recycler.DictionaryEntry

enum class Gender(val str: String) {
    MASCULINE("der"),
    FEMININE("die"),
    NEUTRAL("das")
}

object Translations {
    val entries = arrayListOf(
        DictionaryEntry(
            "Alligator",
            "Alligator",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Ant",
            "Ameise",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Bear",
            "Bär",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Bird",
            "Vogel",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Cat",
            "Katze",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Cow",
            "Kuh",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Deer",
            "Reh",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Dog",
            "Hund",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Donkey",
            "Esel",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Eagle",
            "Adler",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Elephant",
            "Elefant",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Fish",
            "Fisch",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Fox",
            "Fuchs",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Giraffe",
            "Giraffe",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Goat",
            "Ziege",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Hare",
            "Hase",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Horse",
            "Pferd",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Jaguar",
            "Jaguar",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Kangaroo",
            "Känguru",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Kiwi",
            "Kiwi",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Leopard",
            "Leopard",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Lion",
            "Löwe",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Lynx",
            "Luchs",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Manta Ray",
            "Mantarochen",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Mole",
            "Maulwurf",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Newt",
            "Molch",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Octopus",
            "Oktopus",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Ostrich",
            "Strauß",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Parrot",
            "Papagei",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Pig",
            "Schwein",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Quail",
            "Wachtel",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Rabbit",
            "Kaninchen",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Rhinoceros",
            "Nashorn",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Seal",
            "Siegel",
            Gender.NEUTRAL
        ),
        DictionaryEntry(
            "Snake",
            "Schlange",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Tiger",
            "Tiger",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Tortoise",
            "Schildkröte",
            Gender.FEMININE
        ),
        DictionaryEntry(
            "Vulture",
            "Geier",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Weasel",
            "Wiesel",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Wolf",
            "Wolf",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Yak",
            "Yak",
            Gender.MASCULINE
        ),
        DictionaryEntry(
            "Zebra",
            "Zebra",
            Gender.NEUTRAL
        )
    )
}