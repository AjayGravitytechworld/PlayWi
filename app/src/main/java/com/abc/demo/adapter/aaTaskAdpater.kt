package com.abc.demo.adapter

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class aaTaskAdpater(
    private var game_iteams: ArrayList<aaTaskModel>, private val context: Context
) : RecyclerView.Adapter<aaTaskAdpater.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var store: Utilll? = null
    private var db: Database? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)

        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.lblName.text = game_iteams[position].app_name
        holder.lblCoin.text = "" + game_iteams[position].coin

        Glide.with(context).load(game_iteams[position].app_logo)
            .into(holder.imgIcon)

        holder.btnCoin.setOnClickListener {
            play()
            db!!.addContact(
                aaIncome(
                    game_iteams[position].app_name + " Task",
                    "" + game_iteams[position].coin
                )
            )
            store!!.putInt(
                Utilll.str_todayCoin,
                store!!.getInt(Utilll.str_todayCoin) + game_iteams[position].coin
            )
            store!!.putInt(
                Utilll.str_totalCoin,
                store!!.getInt(Utilll.str_totalCoin) + game_iteams[position].coin
            )
            UtilsFiff.task(context, game_iteams[position].app_link)
            Toast.makeText(
                context,
                "You Earned " + game_iteams[position].coin + " Coins",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun getItemCount(): Int {
        return game_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imgIcon: ShapeableImageView = itemView.findViewById(R.id.imgIcon)
        val lblName: MaterialTextView = itemView.findViewById(R.id.lblName)
        val lblCoin: MaterialTextView = itemView.findViewById(R.id.lblCoin)
        val btnCoin: CircularRevealLinearLayout = itemView.findViewById(R.id.btnCoin)
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