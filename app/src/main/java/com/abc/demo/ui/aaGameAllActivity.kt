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
import com.abc.demo.adapter.aaGameAll2_Adpater
import com.abc.demo.adapter.aaGameAll_Adpater
import com.abc.demo.databinding.ActivityGameAllBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class aaGameAllActivity : AppCompatActivity() {

    private var gameAllBinding: ActivityGameAllBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameAllBinding = ActivityGameAllBinding.inflate(layoutInflater)
        setContentView(gameAllBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        gameAllBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        AppManage.getInstance(this).showNativeBanner(
//            gameAllBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

//        AppManage.getInstance(this).showNative(
//            gameAllBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        AppManage.getInstance(this).showNativetype(
            gameAllBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

        if (intent.getIntExtra("type", 0) == 1) {
            gameAllBinding!!.tvProfile.text = resources.getString(R.string.str_gamers)
            gameAllBinding!!.rvGame.adapter =
                aaGameAll_Adpater(UtilsFiff.game_iteams1!!, context!!, 1)
        } else if (intent.getIntExtra("type", 0) == 2) {
            gameAllBinding!!.tvProfile.text = resources.getString(R.string.str_most_games)
            gameAllBinding!!.rvGame.adapter =
                aaGameAll_Adpater(UtilsFiff.game_iteams2!!, context!!, 2)
        } else if (intent.getIntExtra("type", 0) == 3) {
            gameAllBinding!!.tvProfile.text = resources.getString(R.string.str_adventure_games)
            gameAllBinding!!.rvGame.adapter =
                aaGameAll_Adpater(UtilsFiff.game_iteams3!!, context!!, 3)
        } else if (intent.getIntExtra("type", 0) == 4) {
            gameAllBinding!!.tvProfile.text = resources.getString(R.string.str_trending_game)
            gameAllBinding!!.rvGame2.adapter =
                aaGameAll2_Adpater(UtilsFiff.game_iteams4!!, context!!, 4)
        } else if (intent.getIntExtra("type", 0) == 5) {
            gameAllBinding!!.tvProfile.text = resources.getString(R.string.str_popular_games)
            gameAllBinding!!.rvGame2.adapter =
                aaGameAll2_Adpater(UtilsFiff.game_iteams5!!, context!!, 5)
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