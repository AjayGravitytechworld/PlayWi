package com.abc.demo.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abc.demo.R
import com.abc.demo.model.aaLeaderBoardModel
import com.google.android.material.textview.MaterialTextView

class aaLeaderAdpater(
    private val context: Context,
    private var trending_iteams: ArrayList<aaLeaderBoardModel>
) : RecyclerView.Adapter<aaLeaderAdpater.Myvh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.leaderbord_item, parent, false)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.rankNo.text = "" + trending_iteams.get(position).id
        holder.userName.text = trending_iteams.get(position).name
        holder.lblCoin.text = "" + trending_iteams.get(position).coin
    }

    override fun getItemCount(): Int {
        return trending_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val rankNo: MaterialTextView = itemView.findViewById(R.id.rankNo)
        val userName: MaterialTextView = itemView.findViewById(R.id.userName)
        val lblCoin: MaterialTextView = itemView.findViewById(R.id.lblCoin)
    }
}