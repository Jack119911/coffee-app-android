package ninja.parkverbot.coffeeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ninja.parkverbot.coffeeapp.databinding.EntryDeptBinding
import ninja.parkverbot.coffeeapp.model.DebtEntry
import kotlin.math.roundToInt

class MyDebtListEntryRecyclerViewAdapter(
    private val debtEntries: MutableList<DebtEntry>
) : RecyclerView.Adapter<MyDebtListEntryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            EntryDeptBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val debtEntry = debtEntries[position]
        val context = holder.deptEntryTextView.context
        with(debtEntry) {
            holder.deptEntryTextView.text = if (debtEntry.youAreOwing) {
                context.getString(
                    R.string.debts_text_you_owe,
                    counterpart.name,
                    debtItems.find { it.item == "Kaffee" }?.amount?.roundToInt().toString())
            } else {
                context.getString(
                    R.string.debts_text_counterpart_ows,
                    counterpart.name,
                    debtItems.find { it.item == "Kaffee" }?.amount?.roundToInt().toString())
            }
        }
    }

    override fun getItemCount(): Int = debtEntries.size

    inner class ViewHolder(binding: EntryDeptBinding) : RecyclerView.ViewHolder(binding.root) {
        val deptEntryTextView = binding.deptEntryTextView
    }

}