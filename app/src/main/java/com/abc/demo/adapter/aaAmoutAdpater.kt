package com.abc.demo.adapter

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.google.android.material.textview.MaterialTextView

class aaAmoutAdpater(
    private val context: Context,
) : RecyclerView.Adapter<aaAmoutAdpater.Myvh>() {

    private var mediaPlayer: MediaPlayer? = null
    private var store: Utilll? = null
    private var db: Database? = null

    val fruits = arrayOf("500", "1000", "2000", "5000", "10000", "15000", "20000")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.amount_item, parent, false)
        store = Utilll.getInstance(context)
        db = Database(context)
        return Myvh(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Myvh, position: Int) {
        holder.lblAmount.text = fruits[position]

        holder.layoutAmount.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    val rs: Int = store!!.getInt(Utilll.str_totalCoin) / 100

                    if (rs >= fruits[position].toIntOrNull()!!) {
                        store!!.putInt(
                            Utilll.str_withdrowCoin,
                            store!!.getInt(Utilll.str_withdrowCoin) + fruits[position].toIntOrNull()!!
                        )
                        store!!.putInt(
                            Utilll.str_totalCoin,
                            store!!.getInt(Utilll.str_totalCoin) - (fruits[position].toIntOrNull()!! * 100)
                        )

                        winDialog(fruits[position].toIntOrNull()!!, context)
//                        Toast.makeText(
//                            context,
//                            "Your Withdrawal request is in queue.",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            "You Have Not Enough Money to Withdraw.",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    class Myvh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val lblAmount: MaterialTextView = itemView.findViewById(R.id.lblAmount)
        val layoutAmount: CircularRevealRelativeLayout = itemView.findViewById(R.id.layoutAmount)
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

    private fun winDialog(coin: Int, context1: Context) {
        UtilsFiff.alertDialog(
            context1 as Activity,
            R.layout.dialog_withdraww, true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.tex6) as TextView).text = " $coin RS"
                    val c = coin * 100
                    (alertDialog!!.findViewById<View>(R.id.tex616363) as TextView).text =
                        " $coin RS = $c coins"
                    (alertDialog.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        play()
                        store!!.putInt(
                            Utilll.str_speen1Limit,
                            store!!.getInt(Utilll.str_speen1Limit) + 1
                        )
                        alertDialog.dismiss()
                    }
                }
            })
    }
}