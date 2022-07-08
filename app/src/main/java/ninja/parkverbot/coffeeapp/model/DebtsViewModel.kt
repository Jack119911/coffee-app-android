package ninja.parkverbot.coffeeapp.model

import androidx.lifecycle.ViewModel

class DebtsViewModel : ViewModel() {

    private var _debts = listOf<DebtEntry>()
    val debts get() = _debts

    private var _items = listOf<Item>()
    val items get() = _items

    private var _persons = listOf<Person>()
    val persons get() = _persons

    fun setDebts(newDebts: List<DebtEntry>) {
        _debts = newDebts
    }

    fun setItems(newItems: List<Item>) {
        _items = newItems
    }

    fun setPersons(newPersons: List<Person>) {
        _persons = newPersons
    }
}
