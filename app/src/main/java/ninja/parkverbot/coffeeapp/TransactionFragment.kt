package ninja.parkverbot.coffeeapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ninja.parkverbot.coffeeapp.databinding.FragmentTransactionBinding
import ninja.parkverbot.coffeeapp.model.DebtItem
import ninja.parkverbot.coffeeapp.model.DebtsViewModel
import ninja.parkverbot.coffeeapp.model.Person

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel: DebtsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val view = binding.root

        fillPersonSelections()
        fillItemSelection()

        return view
    }

    private fun fillItemSelection() {
        val items = viewModel.items.map { "${it.name} (${it.coffeeUnits})" }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.menu_item_item, items)
        binding.itemSelectionAutoComplete.setAdapter(arrayAdapter)
        binding.itemSelectionAutoComplete.setText("Kaffee (1)", false)
    }

    private fun fillPersonSelections() {
        val names = viewModel.persons.map { it.name }
            .toMutableList()
        names.add(0, getString(R.string.you))

        val arrayAdapterDebtor =
            ArrayAdapter(requireContext(), R.layout.menu_item_name, names.toList())
        binding.debtorSelectionAutoComplete.setAdapter(arrayAdapterDebtor)
        binding.debtorSelectionAutoComplete.setText(getString(R.string.you), false)

        val arrayAdapterCreditor =
            ArrayAdapter(requireContext(), R.layout.menu_item_name, names.toList())
        binding.creditorSelectionAutoComplete.setAdapter(arrayAdapterCreditor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTransactionComplete.setOnClickListener {
            if (binding.creditorSelectionAutoComplete.text.toString().isEmpty()) {
                binding.creditorSelection.error = getString(R.string.choose_a_person)
            } else {
                addTransaction()
                findNavController().popBackStack()
            }
        }

        binding.debtorSelectionAutoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _->
                if (binding.debtorSelectionAutoComplete.text.toString() != getString(R.string.you)) {
                    binding.creditorSelectionAutoComplete.setText(getString(R.string.you), false)
                }
            }

        binding.creditorSelectionAutoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _->
                if (binding.creditorSelectionAutoComplete.text.toString() != getString(R.string.you)) {
                    binding.debtorSelectionAutoComplete.setText(getString(R.string.you), false)
                }
            }
    }

    private fun addTransaction() {
        val debtor = binding.debtorSelectionAutoComplete.text.toString()
        val creditor = binding.creditorSelectionAutoComplete.text.toString()
        val youAreOwing: Boolean
        val counterpart: Person
        if (debtor == getString(R.string.you)) {
            youAreOwing = true
            counterpart = viewModel.persons.find { it.name == creditor }!!
        } else {
            youAreOwing = false
            counterpart = viewModel.persons.find { it.name == debtor }!!
        }
        val debtItemName = binding.itemSelectionAutoComplete.text
            .toString()
            .takeWhile { it != ' ' }
        val debtItemAmount = viewModel.items.find { it.name == debtItemName }!!.coffeeUnits
        viewModel.addTransaction(
            counterpart,
            DebtItem("Kaffee", debtItemAmount.toFloat()),
            youAreOwing
        )
    }
}