package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivityScratchBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.anupkumarpanwar.scratchview.ScratchView
import com.anupkumarpanwar.scratchview.ScratchView.IRevealListener
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class aaScratchActivity : AppCompatActivity() {
    var scratchBinding: ActivityScratchBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null

    val numbers: IntArray = intArrayOf(100, 200, 300, 150, 250)

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scratchBinding = ActivityScratchBinding.inflate(layoutInflater)
        setContentView(scratchBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

        scratchBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        AppManage.getInstance(this).showNativeBanner(
//            scratchBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(this).showNativetype(
            scratchBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

//        AppManage.getInstance(this).showNative(
//            scratchBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        scratchBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        scratchBinding!!.btnSpinWheel.setOnClickListener(View.OnClickListener {
            val mIntent = intent
            finish()
            startActivity(mIntent)
        })
        if (store!!.getInt(Utilll.str_scratchLimit) < 5) {
//            scratchBinding!!.card1.visibility = View.VISIBLE
            scratchBinding!!.lblCArd.text = resources.getString(R.string.str_scretch_card_text)
        } else {
//            scratchBinding!!.card1.visibility = View.GONE
            scratchBinding!!.lblCArd.text = resources.getString(R.string.str_scretch_card_text2)
        }

        scratchBinding!!.lblLimit.text =
            resources.getString(R.string.str_scratch_card) + " ( " + store!!.getInt(Utilll.str_scratchLimit) + " / 5 )"

        scratchBinding!!.scratchView.setRevealListener(object : IRevealListener {
            override fun onRevealed(scratchView: ScratchView) {

                if (store!!.getInt(Utilll.str_scratchLimit) < 5) {
                    val random1 = (0..4).shuffled().last()
                    scratchBinding!!.point.text = "You've won \n " + numbers[random1] + " Coins"
                    store!!.putInt(
                        Utilll.str_scratchLimit,
                        store!!.getInt(Utilll.str_scratchLimit) + 1
                    )
                    winDialog(numbers[random1])
                } else {
                    Toast.makeText(context, "Your Daily Scratch Card Limit is Over", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onRevealPercentChangedListener(scratchView: ScratchView, percent: Float) {
                Log.d("Revealed", percent.toString())
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBackPressed() {
        play()
        AppManage.getInstance(context).showInterstitialAd(
            context,
            {
                onBackPressedDispatcher.onBackPressed()
                Animatoo.animateSlideLeft(context!!)
            },
            "",
            AppManage.app_innerClickCntSwAd
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun winDialog(coin: Int) {
        scratchBinding!!.lblLimit.text =
            resources.getString(R.string.str_scratch_card) + " ( " + store!!.getInt(Utilll.str_scratchLimit) + " / 5 )"

        UtilsFiff.alertDialog(this,
            R.layout.dialog_scretch, true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.tex6) as TextView).text = " $coin Coins"
                    (alertDialog!!.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        play()
                        updateCoin(coin)
                        alertDialog.dismiss()
                    }
                }
            })
    }

    private fun updateCoin(coin: Int) {
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + coin)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + coin)

        db!!.addContact(
            aaIncome(
                "Scratch Card " + store!!.getString(Utilll.str_currentDate),
                "" + coin
            )
        )

        scratchBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)
    }
}