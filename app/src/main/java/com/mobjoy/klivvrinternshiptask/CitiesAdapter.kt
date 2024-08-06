package com.mobjoy.klivvrinternshiptask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobjoy.klivvrinternshiptask.data.CitiesResponseItem
import com.mobjoy.klivvrinternshiptask.databinding.CityItemLayoutBinding

class CitiesAdapter(private var list: List<CitiesResponseItem?>?) :
    RecyclerView.Adapter<CitiesAdapter.MyViewHolder>() {
    class MyViewHolder(val viewBinding: CityItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = CityItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(view)
    }

    fun bindCitiesList(data: List<CitiesResponseItem?>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list?.get(position)
        if (item != null) {
            holder.viewBinding.cityIdTV.text = item._id.toString()
            holder.viewBinding.cityNameTV.text = item.name.toString()
            holder.viewBinding.countryTV.text = item.country.toString()
            holder.viewBinding.latTV.text = item.coord?.lat.toString()
            holder.viewBinding.longTV.text = item.coord?.lon.toString()
        }


    }


}
