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
                    "Coffee",
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
                    "Coffee",
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
                    "Coffee",
                    0.3F
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
            "Coffee",
            1
        ),
        Item(
            "Beer",
            1
        ),
        Item(
            "Doener",
            2
        ),
        Item(
            "Dinner",
            4
        ),
    )

}