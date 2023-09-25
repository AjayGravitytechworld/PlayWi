package com.abc.demo.adapter

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.model.aaIncome
import com.abc.demo.model.aaTaskModel
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.bumptech.glide.Glide

class Adpater_taskaa(private var state_iteams: ArrayList<aaTaskModel>, private val context: Context) : RecyclerView.Adapter<Adpater_taskaa.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null
    private var store: Utilll? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.todaytask_iteam, parent, false)

        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {

        Glide.with(context).load(state_iteams[position].app_logo)
            .into(holder.img)

        holder.coin.text = "" + state_iteams[position].coin

        holder.img.setOnClickListener {
            play()
            db!!.addContact(
                aaIncome(
                    state_iteams[position].app_name + " Task",
                    "" + state_iteams[position].coin
                )
            )
            store!!.putInt(
                Utilll.str_todayCoin,
                store!!.getInt(Utilll.str_todayCoin) + state_iteams[position].coin
            )
            store!!.putInt(
                Utilll.str_totalCoin,
                store!!.getInt(Utilll.str_totalCoin) + state_iteams[position].coin
            )
            UtilsFiff.task(context, state_iteams[position].app_link)
            Toast.makeText(
                context,
                "You Earned " + state_iteams[position].coin + " Coins",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun getItemCount(): Int {
        return state_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val coin: TextView = itemView.findViewById(R.id.coin)
        val img: ImageView = itemView.findViewById(R.id.img)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(context, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }
}