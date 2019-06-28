package com.alejandro.hob.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.hob.R
import com.alejandro.hob.data.remote.Brastlewark
import com.bumptech.glide.Glide

class PopulationAdapter(val gnomeList: List<Brastlewark>, var gnomeListFiltered: List<Brastlewark> = gnomeList) : RecyclerView.Adapter<PopulationAdapter.PopuViewHolder>(), Filterable {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopuViewHolder(layoutInflater.inflate(com.alejandro.hob.R.layout.item_population, parent, false))
    }

    override fun getItemCount(): Int {
        return gnomeListFiltered.size
    }

    override fun onBindViewHolder(holder: PopuViewHolder, position: Int) {
        holder.txtName.text = gnomeList.get(position).name
        Glide.with(holder.itemView)
            .load(gnomeList.get(position).thumbnail)
            .fitCenter()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onItemSelected(gnomeList.get(position))
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    gnomeListFiltered = gnomeList
                } else {
                    val filteredList = ArrayList<Brastlewark>()
                    for (item in gnomeList) {
                        if (item.name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(item)
                        }
                    }
                    gnomeListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = gnomeListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                gnomeListFiltered = filterResults.values as ArrayList<Brastlewark>
                notifyDataSetChanged()
            }
        }
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class PopuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.name)
        val image = itemView.findViewById<ImageView>(R.id.image)
    }

    interface OnItemClickListener {
        fun onItemSelected(brastlewark: Brastlewark)
    }
}