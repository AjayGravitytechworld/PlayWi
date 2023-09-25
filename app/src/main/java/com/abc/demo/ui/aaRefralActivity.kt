package com.abc.demo.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import com.abc.demo.BuildConfig
import com.abc.demo.R
import com.abc.demo.databinding.ActivityRefralBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class aaRefralActivity : AppCompatActivity() {

    private var refralBinding: ActivityRefralBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var code: String? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refralBinding = ActivityRefralBinding.inflate(layoutInflater)
        setContentView(refralBinding!!.root)

        context = this
        store = Utilll.getInstance(this)

        code = intent.getStringExtra("code")

        AppManage.getInstance(this).showNativeBanner(
            refralBinding!!.bannerContainer,
            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
        )

        refralBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }


        refralBinding!!.btnRefer.setOnClickListener {
            play()
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    resources.getString(R.string.app_name)
                )
                var shareMessage = resources.getString(R.string.str_recomondet)
                shareMessage =
                    """
                ${shareMessage + " https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID}
                
                
                """.trimIndent()

                shareMessage =
                    shareMessage + "Invite Code :  " + code

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        resources.getString(R.string.str_choose_one)
                    )
                )
            } catch (e: Exception) {
            }
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