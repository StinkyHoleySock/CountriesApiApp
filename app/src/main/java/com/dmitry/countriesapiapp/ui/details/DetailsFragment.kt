package com.dmitry.countriesapiapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dmitry.countriesapiapp.applyVisibility
import com.dmitry.coutriesapiapp.R
import com.dmitry.coutriesapiapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    lateinit var viewModel: DetailsViewModel
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        _binding = FragmentDetailsBinding.bind(view)

        viewModel.getCountryDetails(args.name)
        viewModel.countryDetails.observe(viewLifecycleOwner) { country ->

            binding.tvName.text = args.name
            binding.tvCapital.text = requireContext().getString(R.string.capital, country.capital)
            binding.tvCurrency.text = requireContext().getString(
                R.string.currency,
                country.currencies?.get(0)?.name,
                country.currencies?.get(0)?.symbol
            )
            binding.tvRegion.text = requireContext().getString(R.string.region, country.region)
            binding.tvTimezone.text =
                requireContext().getString(R.string.timezone, country.timezones)

            Glide.with(requireContext())
                .load(country.flags?.png ?: country.flags?.svg)
                .into(binding.ivFlag)

        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.mainContainer.applyVisibility(!isLoading)
            binding.progressCircular.applyVisibility(isLoading)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}