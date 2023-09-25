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
import com.abc.demo.databinding.ActivityFeedbackBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import com.abc.demo.ads.AppManage
import java.util.Objects

class aaFeedbackActivity : AppCompatActivity() {

    private var feedbackBinding: ActivityFeedbackBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedbackBinding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(feedbackBinding!!.root)

        context = this
        store = Utilll.getInstance(this)

//        AppManage.getInstance(this).showNative(
//            feedbackBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )
//
//        AppManage.getInstance(this).showNativeBanner(
//            feedbackBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(this).showNativetype(
            feedbackBinding!!.bannerContainer,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 3
        )

        feedbackBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        feedbackBinding!!.btnSubmit.setOnClickListener {
            play()
            if (Objects.requireNonNull(feedbackBinding!!.textTitle.text)!!.isEmpty()) {
                Snackbar.make(
                    feedbackBinding!!.root,
                    resources.getString(R.string.text_no_title),
                    Snackbar.LENGTH_LONG
                ).show()
            } else if (Objects.requireNonNull(feedbackBinding!!.textDesc.text)!!.isEmpty()
            ) {
                Snackbar.make(
                    feedbackBinding!!.root,
                    resources.getString(R.string.text_no_desc),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val email = Intent(Intent.ACTION_SEND)
                email.putExtra(Intent.EXTRA_EMAIL, "")
                email.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
                email.putExtra(Intent.EXTRA_TEXT, "")
                email.type = "message/rfc822"
                startActivity(Intent.createChooser(email, "Choose an Email client :"))
                Snackbar.make(
                    feedbackBinding!!.root,
                    resources.getString(R.string.text_rise_ticket),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Deprecated("Deprecated in Java")
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

        if (UtilsFiff.sound == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.click)
            mediaPlayer!!.start()
        }
    }
}