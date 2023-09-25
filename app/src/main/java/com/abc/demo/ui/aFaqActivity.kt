package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.databinding.ActivityFaqBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class aFaqActivity : AppCompatActivity() {

    private var faqBinding: ActivityFaqBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        faqBinding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(faqBinding!!.root)



        context = this
        store = Utilll.getInstance(this)

//        AppManage.getInstance(this).showNative(
//            faqBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        AppManage.getInstance(this).showNativeBanner(
            faqBinding!!.bannerContainer,
            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
        )

        faqBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        faqBinding!!.arrow1.setOnClickListener { _ ->
            if (faqBinding!!.child1.visibility === View.GONE) {
                faqBinding!!.child1.visibility = View.VISIBLE
                faqBinding!!.arrow1.setImageResource(R.drawable.arrup)
            } else {
                faqBinding!!.child1.visibility = View.GONE
                faqBinding!!.arrow1.setImageResource(R.drawable.arrdown)
            }
        }

        faqBinding!!.arrow2.setOnClickListener { _ ->
            if (faqBinding!!.child2.visibility === View.GONE) {
                faqBinding!!.child2.visibility = View.VISIBLE
                faqBinding!!.arrow2.setImageResource(R.drawable.arrup)
            } else {
                faqBinding!!.child2.visibility = View.GONE
                faqBinding!!.arrow2.setImageResource(R.drawable.arrdown)
            }
        }

        faqBinding!!.arrow3.setOnClickListener { _ ->
            if (faqBinding!!.child3.visibility === View.GONE) {
                faqBinding!!.child3.visibility = View.VISIBLE
                faqBinding!!.arrow3.setImageResource(R.drawable.arrup)
            } else {
                faqBinding!!.child3.visibility = View.GONE
                faqBinding!!.arrow3.setImageResource(R.drawable.arrdown)
            }
        }

        faqBinding!!.arrow4.setOnClickListener { _ ->
            if (faqBinding!!.child4.visibility === View.GONE) {
                faqBinding!!.child4.visibility = View.VISIBLE
                faqBinding!!.arrow4.setImageResource(R.drawable.arrup)
            } else {
                faqBinding!!.child4.visibility = View.GONE
                faqBinding!!.arrow4.setImageResource(R.drawable.arrdown)
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