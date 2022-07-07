package ninja.parkverbot.coffeeapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData

import ninja.parkverbot.coffeeapp.placeholder.PlaceholderContent.PlaceholderItem
import ninja.parkverbot.coffeeapp.databinding.EntryDeptBinding
import ninja.parkverbot.coffeeapp.model.DebtEntry
import kotlin.math.roundToInt

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyDebtListEntryRecyclerViewAdapter(
    private val debtEntries: List<DebtEntry>
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
                    debtItems.find { it.item.equals("Coffee") }?.amount?.roundToInt().toString())
            } else {
                context.getString(
                    R.string.debts_text_counterpart_ows,
                    counterpart.name,
                    debtItems.find { it.item.equals("Coffee") }?.amount?.roundToInt().toString())
            }
        }
    }

    override fun getItemCount(): Int = debtEntries.size

    inner class ViewHolder(binding: EntryDeptBinding) : RecyclerView.ViewHolder(binding.root) {
        val deptEntryTextView = binding.deptEntryTextView
    }

}