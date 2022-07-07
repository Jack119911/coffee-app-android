package ninja.parkverbot.coffeeapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DebtsViewModel : ViewModel() {

    private var _debts = listOf<DebtEntry>()
    val debts get() = _debts

    fun setDebts(newDebts: List<DebtEntry>) {
        _debts = newDebts
    }
}
