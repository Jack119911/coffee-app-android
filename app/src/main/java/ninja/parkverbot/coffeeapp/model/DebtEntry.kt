package ninja.parkverbot.coffeeapp.model

data class DebtEntry(val counterpart: Person, val debtItems: List<DebtItem>, val youAreOwing: Boolean)
