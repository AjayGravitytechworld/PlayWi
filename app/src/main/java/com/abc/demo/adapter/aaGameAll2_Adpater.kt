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
import com.abc.demo.model.aaGameModel1
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.google.android.material.imageview.ShapeableImageView

class aaGameAll2_Adpater(
    private var game_iteams: ArrayList<aaGameModel1>,
    private val context: Context,
    private val type: Int
) : RecyclerView.Adapter<aaGameAll2_Adpater.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var store: Utilll? = null
    private var db: Database? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.trending_popular_item, parent, false)
        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.Game.setImageResource(game_iteams[position].image)
        holder.Game.setOnClickListener {
            play()
            db!!.addContact(aaIncome("Playing Game ", "20"))
            store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 20)
            store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 20)
            UtilsFiff.openGame(context, game_iteams[position].link)
            Toast.makeText(context, "You Earned 20 Coins", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return game_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val Game: ShapeableImageView = itemView.findViewById(R.id.Game)
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