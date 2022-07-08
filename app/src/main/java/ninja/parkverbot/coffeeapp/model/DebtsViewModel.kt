package ninja.parkverbot.coffeeapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DebtsViewModel : ViewModel() {

    private var _debts =  MutableLiveData<MutableList<DebtEntry>>()
    val debts get() = _debts

    private var _items = listOf<Item>()
    val items get() = _items

    private var _persons = listOf<Person>()
    val persons get() = _persons

    fun setDebts(newDebts: List<DebtEntry>) {
        _debts.value = newDebts.toMutableList()
    }

    fun setItems(newItems: List<Item>) {
        _items = newItems
    }

    fun setPersons(newPersons: List<Person>) {
        _persons = newPersons
    }

    fun addTransaction(counterpart: Person, value: DebtItem, youAreOwing: Boolean) {
        val entry = debts.value?.find { it.counterpart == counterpart }
        if (entry != null)
            modifyEntry(entry, value, youAreOwing)
        else
            addEntry(counterpart, value, youAreOwing)
    }

    private fun modifyEntry(entry: DebtEntry, changeValue: DebtItem, youAreOwing: Boolean) {
        if (entry.youAreOwing == youAreOwing) {
            // Increase Amount
            entry.debtItems.find { it.item == "Kaffee"}!!.amount += changeValue.amount
        } else {
            // Reduce Amount
            val currentValue = entry.debtItems.find { it.item == "Kaffee" }!!
            if (currentValue.amount >= changeValue.amount) {
                currentValue.amount -= changeValue.amount
            } else {
                entry.youAreOwing = !entry.youAreOwing
                currentValue.amount = changeValue.amount - currentValue.amount
            }
        }
    }

    private fun addEntry(counterpart: Person, value: DebtItem, youAreOwing: Boolean) {
        val entry = DebtEntry(
            counterpart,
            listOf(value),
            youAreOwing
        )
        _debts.value?.add(entry)
    }
}
