package com.dmitry.countriesapiapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dmitry.coutriesapiapp.R
import com.dmitry.coutriesapiapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        val currencyName = args.country.currencies[0].name ?: "No currency name"
        val currencySymbol = args.country.currencies[0].symbol ?: "No currency symbol"
        val region = args.country.region ?: "No region"
        val timezones = args.country.timezones ?: "No timezone"
        val imageLink = args.country.flags.png ?: args.country.flags.svg

        with(binding) {
            tvName.text = args.country.name
            tvCapital.text = requireContext().getString(R.string.capital, args.country.capital)
            tvCurrency.text = requireContext().getString(
                R.string.currency,
                currencyName,
                currencySymbol
            )
            tvRegion.text = requireContext().getString(R.string.region, region)
            tvTimezone.text = requireContext().getString(R.string.timezone, timezones)

            Glide.with(requireContext())
                .load(imageLink)
                .into(ivFlag)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}