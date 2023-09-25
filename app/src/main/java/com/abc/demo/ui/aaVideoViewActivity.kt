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
import com.abc.demo.databinding.ActivityVideoViewBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class aaVideoViewActivity : AppCompatActivity() {

    private var videoViewBinding: ActivityVideoViewBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoViewBinding = ActivityVideoViewBinding.inflate(layoutInflater)
        setContentView(videoViewBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        AppManage.getInstance(this).showNativetype(
            videoViewBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        videoViewBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        videoViewBinding!!.andExoPlayerView.setSource(intent.getStringExtra("link").toString())
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        play()
        videoViewBinding!!.andExoPlayerView.releasePlayer()
        AppManage.getInstance(context).showInterstitialAd(
            context,
            { onBackPressedDispatcher.onBackPressed() },
            "",
            AppManage.app_innerClickCntSwAd
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play(){
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