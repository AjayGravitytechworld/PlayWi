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
import com.abc.demo.model.aaGameModel
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class aaGameAll_Adpater(
    private var game_iteams: ArrayList<aaGameModel>,
    private val context: Context,
    private val type: Int
) : RecyclerView.Adapter<aaGameAll_Adpater.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var store: Utilll? = null
    private var db: Database? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)

        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.game_img.setImageResource(game_iteams[position].image)
        holder.game_txt.text = game_iteams[position].name

        holder.game_btn.setOnClickListener {
            play()
            db!!.addContact(aaIncome("Playing Game " + game_iteams[position].name, "20"))
            store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 20)
            store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 20)
            UtilsFiff.openGame(context, game_iteams[position].link)
            Toast.makeText(context, "You Earned 20 Coins", Toast.LENGTH_SHORT)
                .show()
        }

        when (type) {
            1 -> {
                holder.game_bg.setBackgroundResource(R.drawable.allgame_bg)
                holder.game_btn.setBackgroundResource(R.drawable.btncontinuebg)
            }

            2 -> {
                holder.game_bg.setBackgroundResource(R.drawable.m_gamebg)
                holder.game_btn.setBackgroundResource(R.drawable.m_gamebtn)
            }

            3 -> {
                holder.game_bg.setBackgroundResource(R.drawable.adventur_bg)
                holder.game_btn.setBackgroundResource(R.drawable.adventur_btnbg)
            }
        }
    }

    override fun getItemCount(): Int {
        return game_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val game_bg: CircularRevealLinearLayout = itemView.findViewById(R.id.game_bg)
        val game_img: ShapeableImageView = itemView.findViewById(R.id.game_img)
        val game_txt: MaterialTextView = itemView.findViewById(R.id.game_txt)
        val game_btn: CircularRevealRelativeLayout = itemView.findViewById(R.id.game_btn)
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