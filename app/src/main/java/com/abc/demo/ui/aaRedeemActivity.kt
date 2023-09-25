package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.abc.demo.R
import com.abc.demo.adapter.aaAmoutAdpater
import com.abc.demo.databinding.ActivityRedeemBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class aaRedeemActivity : AppCompatActivity() {
    private var redeemBinding: ActivityRedeemBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redeemBinding = ActivityRedeemBinding.inflate(layoutInflater)
        setContentView(redeemBinding!!.root)

        context = this
        store = Utilll.getInstance(this)

//        AppManage.getInstance(this).showNativeBanner(
//            redeemBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(context).showNativetype(
            redeemBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(this).showNative(
//            redeemBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        redeemBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        redeemBinding!!.btnPaytm.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    Toast.makeText(context!!, "You Have Not Enough Money to Withdraw.", Toast.LENGTH_SHORT)
                        .show()
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        redeemBinding!!.btnPaypal.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    Toast.makeText(context!!, "You Have Not Enough Money to Withdraw.", Toast.LENGTH_SHORT)
                        .show()
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }
        redeemBinding!!.listAmount.adapter = aaAmoutAdpater(context!!)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        play()
        AppManage.getInstance(context).showInterstitialAd(
            context,
            { onBackPressedDispatcher.onBackPressed() },
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

    override fun onResume() {
        super.onResume()
        redeemBinding!!.lblCoin.text = "" + store!!.getInt(Utilll.str_totalCoin)
        val rs: Int = store!!.getInt(Utilll.str_totalCoin) / 100
        redeemBinding!!.rupees.text = "₹ $rs"
    }
}