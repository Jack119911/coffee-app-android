package ninja.parkverbot.coffeeapp.data

import ninja.parkverbot.coffeeapp.model.DebtEntry
import ninja.parkverbot.coffeeapp.model.DebtItem
import ninja.parkverbot.coffeeapp.model.Item
import ninja.parkverbot.coffeeapp.model.Person

class MockData {

    val debts = listOf<DebtEntry>(
        DebtEntry(
            Person(
                1,
                "Matthias"
            ),
            listOf(
                DebtItem(
                    "Kaffee",
                    3F
                ),
                DebtItem(
                    "Doener",
                    1F
                )
            ),
            true
        ),
        DebtEntry(
            Person(
                2,
                "Marcel"
            ),
            listOf(
                DebtItem(
                    "Kaffee",
                    1F
                )
            ),
            false
        ),
        DebtEntry(
            Person(
                3,
                "Michi"
            ),
            listOf(
                DebtItem(
                    "Kaffee",
                    2F
                ),
                DebtItem(
                    "Pitcher",
                    0.5F
                )
            ),
            true
        ),
    )

    val items = listOf<Item>(
        Item(
            "Kaffee",
            1
        ),
        Item(
            "Bier",
            1
        ),
        Item(
            "Doener",
            2
        ),
        Item(
            "Essen",
            4
        ),
    )

    val persons = listOf<Person>(
        Person(
            1,
            "Matthias"
        ),
        Person(
            2,
            "Marcel"
        ),
        Person(
            3,
            "Michi"
        ),
        Person(
            4,
            "Leon"
        ),

    )

}