package com.abc.demo.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.adapter.aaIncomeAdpater
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivityIncomeBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class IncomeActivity : AppCompatActivity() {

    private var incomeBinding: ActivityIncomeBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    private var db: Database? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        incomeBinding = ActivityIncomeBinding.inflate(layoutInflater)
        setContentView(incomeBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        incomeBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        AppManage.getInstance(this).showNativetype(
            incomeBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

        db = Database(context)
        incomeBinding!!.rvLeader1.adapter = aaIncomeAdpater(db!!.allContacts)

        incomeBinding!!.btnshare.setOnClickListener {
            play()
            UtilsFiff.shareApp(context!!)
        }

        incomeBinding!!.texcoin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        incomeBinding!!.btnReedem.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, aaRedeemActivity::class.java))
                    Animatoo.animateZoom(context!!)
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
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
}