package ninja.parkverbot.coffeeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ninja.parkverbot.coffeeapp.data.MockData
import ninja.parkverbot.coffeeapp.databinding.FragmentDebtListBinding
import ninja.parkverbot.coffeeapp.model.DebtsViewModel

/**
 * A fragment representing a list of Items.
 */
class DebtEntryListFragment : Fragment() {

    private val viewModel: DebtsViewModel by viewModels()
    private lateinit var binding: FragmentDebtListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setDebts(MockData().debts)
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
            it.findNavController().navigate(R.id.action_deptEntryListFragment_to_transactionFragment)
        }
    }

}