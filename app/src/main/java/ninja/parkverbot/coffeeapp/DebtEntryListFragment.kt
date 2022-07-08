package ninja.parkverbot.coffeeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ninja.parkverbot.coffeeapp.databinding.FragmentDebtListBinding
import ninja.parkverbot.coffeeapp.model.DebtsViewModel

/**
 * A fragment representing a list of Items.
 */
class DebtEntryListFragment : Fragment() {

    private val viewModel: DebtsViewModel by activityViewModels()
    private lateinit var binding: FragmentDebtListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDebtListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        val recyclerView: RecyclerView = binding.deptItemList
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyDebtListEntryRecyclerViewAdapter(viewModel.debts)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNewTransaction.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_deptEntryListFragment_to_transactionFragment)
        }
    }

}