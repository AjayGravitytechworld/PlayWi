package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import com.abc.demo.R
import com.abc.demo.adapter.aaMoreVideoAdpater
import com.abc.demo.databinding.ActivityMoreVideoBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class aaMoreVideoActivity : AppCompatActivity() {

    private var moreVideoBinding: ActivityMoreVideoBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    var type: Int? = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moreVideoBinding = ActivityMoreVideoBinding.inflate(layoutInflater)
        setContentView(moreVideoBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        type = intent.getIntExtra("type", 0)

//        AppManage.getInstance(this).showNativeBanner(
//            moreVideoBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

//        AppManage.getInstance(this).showNative(
//            moreVideoBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        AppManage.getInstance(this).showNativetype(
            moreVideoBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

        when (type) {
            1 -> {
                moreVideoBinding!!.list.adapter =
                    aaMoreVideoAdpater(context!!, UtilsFiff.today_video!!)
            }

            2 -> {
                moreVideoBinding!!.list.adapter =
                    aaMoreVideoAdpater(context!!, UtilsFiff.trending_video!!)
            }

            3 -> {
                moreVideoBinding!!.list.adapter =
                    aaMoreVideoAdpater(context!!, UtilsFiff.popular_video!!)
            }
        }

        moreVideoBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }
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
}