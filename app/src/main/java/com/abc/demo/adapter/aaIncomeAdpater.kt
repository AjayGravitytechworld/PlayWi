package com.abc.demo.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abc.demo.R
import com.abc.demo.model.aaIncome
import com.google.android.material.textview.MaterialTextView

class aaIncomeAdpater(
    private var trending_iteams: List<aaIncome>) : RecyclerView.Adapter<aaIncomeAdpater.Myvh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.income_item, parent, false)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.incom_coin.text = trending_iteams[position].phoneNumber
        holder.incom_name.text = trending_iteams[position].name
    }

    override fun getItemCount(): Int {
        return trending_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val incom_coin: MaterialTextView = itemView.findViewById(R.id.incom_coin)
        val incom_name: MaterialTextView = itemView.findViewById(R.id.incom_name)
    }
}