package com.dmitry.countriesapiapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.countriesapiapp.model.Country
import com.dmitry.coutriesapiapp.databinding.ItemCountryBinding

class CountriesAdapter(
    private val countryClickListener: (name: String) -> Unit,
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    private var list: MutableList<Country> = mutableListOf()

    fun setData(data: List<Country>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Country) {
            binding.tvName.text = response.name
            binding.root.setOnClickListener { countryClickListener(response.name!!) }
        }
    }

    override fun getItemCount() = list.size
}
