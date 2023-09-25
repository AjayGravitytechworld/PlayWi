package com.abc.demo.adapter

import android.content.Context
import android.content.Intent
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
import com.abc.demo.model.aaVideoModel
import com.abc.demo.ui.aaVideoViewActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.abc.demo.ads.AppManage

class aaTrendingAdpater(
    private val context: Context,
    private var trending_iteams: ArrayList<aaVideoModel>
) : RecyclerView.Adapter<aaTrendingAdpater.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var store: Utilll? = null
    private var db: Database? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.trendingvideo_item, parent, false)
        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        Glide.with(context).load(trending_iteams[position].image)
            .into(holder.image)

        holder.image.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    db!!.addContact(aaIncome("Trending Watch Video", "25"))
                    store!!.putInt(
                        Utilll.str_todayCoin,
                        store!!.getInt(Utilll.str_todayCoin) + 25
                    )
                    store!!.putInt(
                        Utilll.str_totalCoin,
                        store!!.getInt(Utilll.str_totalCoin) + 25
                    )
                    context.startActivity(
                        Intent(context, aaVideoViewActivity::class.java).putExtra(
                            "link",
                            trending_iteams[position].image
                        )
                    )
                    Toast.makeText(context, "You Earned 25 Coins", Toast.LENGTH_SHORT)
                        .show()
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
    }

    override fun getItemCount(): Int {
        return trending_iteams.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ShapeableImageView = itemView.findViewById(R.id.image)
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