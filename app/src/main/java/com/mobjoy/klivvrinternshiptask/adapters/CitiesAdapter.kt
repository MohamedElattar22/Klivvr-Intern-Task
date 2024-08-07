package com.mobjoy.klivvrinternshiptask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobjoy.domain.model.CitiesResponseItem
import com.mobjoy.klivvrinternshiptask.databinding.CityItemLayoutBinding

class CitiesAdapter(private var list: List<CitiesResponseItem?>?) :
    RecyclerView.Adapter<CitiesAdapter.MyViewHolder>() {

    var onCityClickListener: OnCityClickListener? = null
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
        onCityClickListener?.let { onCityClickListener ->
            holder.viewBinding.toMapBtn.setOnClickListener {
                onCityClickListener.onClick(item!!, position)
            }
        }


    }

    fun interface OnCityClickListener {
        fun onClick(cityItem: CitiesResponseItem, position: Int)

    }


}
