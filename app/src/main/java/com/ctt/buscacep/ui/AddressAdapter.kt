package com.ctt.buscacep.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ctt.buscacep.R
import com.ctt.buscacep.model.Address

class AddressAdapter(
        private val addressList: List<Address>
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAddress: TextView = itemView.findViewById(R.id.txt_address)
        val txtNeighbourhood: TextView = itemView.findViewById(R.id.txt_neighbourhood)
        val txtState: TextView = itemView.findViewById(R.id.txt_state)
        val txtCep: TextView = itemView.findViewById(R.id.txt_cep)
        val txtCity: TextView = itemView.findViewById(R.id.txt_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {
        holder.apply {
            txtAddress.text = addressList[position].street
            txtNeighbourhood.text = addressList[position].neighbourhood
            txtState.text = addressList[position].state
            txtCity.text = addressList[position].city
            txtCep.text = addressList[position].postcode
        }
    }

    override fun getItemCount(): Int = addressList.size

}

