package com.dmitry.countriesapiapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitry.countriesapiapp.NetworkUtil
import com.dmitry.countriesapiapp.applyVisibility
import com.dmitry.countriesapiapp.model.Country
import com.dmitry.coutriesapiapp.R
import com.dmitry.coutriesapiapp.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    lateinit var viewModel: ListViewModel
    private val countriesAdapter by lazy {
        CountriesAdapter() {
            navigateToDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        _binding = FragmentListBinding.bind(view)

        binding.rvCountries.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = countriesAdapter
        }

        binding.fabRefresh.setOnClickListener {
            viewModel.getCountries()
        }

        viewModel.listOfCountries.observe(viewLifecycleOwner) {
            countriesAdapter.setData(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.rvCountries.applyVisibility(!isLoading)
            binding.progressCircular.applyVisibility(isLoading)
        }

        val networkConnection = NetworkUtil(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) viewModel.getCountries()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToDetails(country: Country) {
        val direction = ListFragmentDirections.actionListFragmentToDetailsFragment(country)
        findNavController().navigate(direction)
    }
}