package ninja.parkverbot.coffeeapp.model

data class DebtEntry(var counterpart: Person, var debtItems: List<DebtItem>, var youAreOwing: Boolean)
