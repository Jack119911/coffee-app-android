package ninja.parkverbot.coffeeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ninja.parkverbot.coffeeapp.databinding.FragmentTransactionBinding
import ninja.parkverbot.coffeeapp.model.DebtsViewModel

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
        binding.itemSelectionAutoComplete.setText("Kaffee", false)
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
            // TODO("Post transaction to API")
            findNavController().popBackStack()
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
}