package com.abc.demo.ui

import android.annotation.SuppressLint
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
import com.abc.demo.databinding.ActivityIntroBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class IntroActivity : AppCompatActivity() {

    private var introBinding: ActivityIntroBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    var iiii = 1

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        introBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        AppManage.getInstance(this).showNativetype(
            introBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 3
        )

        introBinding!!.img1.setImageResource(R.drawable.introbggg1)

        introBinding!!.lbl1.setOnClickListener {
            play()
            when (iiii) {
                1 -> {
                    iiii++
                    introBinding!!.img1.setImageResource(R.drawable.introbggg2)
                }
                2 -> {
                    iiii++
                    introBinding!!.img1.setImageResource(R.drawable.introbggg3)
                    introBinding!!.lbl1.text = "GO"
                }
                else -> {
                    AppManage.getInstance(this@IntroActivity).showInterstitialAd(
                        this@IntroActivity,
                        {
                            store!!.putInt(Utilll.str_intro, 1)
                            if (store!!.getInt(Utilll.str_isLogin) == 0) {
                                startActivity(
                                    Intent(
                                        this@IntroActivity,
                                        aaLoginActivity::class.java
                                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                )
                            } else if (store!!.getString(Utilll.str_language).equals("")) {
                                startActivity(
                                    Intent(
                                        this@IntroActivity,
                                        LanguagesActivity::class.java
                                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                )
                            } else {
                                startActivity(
                                    Intent(
                                        this@IntroActivity,
                                        aaStartActivity::class.java
                                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                )
                            }
                        },
                        "",
                        AppManage.app_mainClickCntSwAd
                    )
                }
            }
        }

        introBinding!!.startbtn.setOnClickListener {
            play()
            AppManage.getInstance(this@IntroActivity).showInterstitialAd(
                this@IntroActivity,
                {
                    store!!.putInt(Utilll.str_intro, 1)
                    if (store!!.getInt(Utilll.str_isLogin) == 0) {
                        startActivity(
                            Intent(
                                this@IntroActivity,
                                aaLoginActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    } else if (store!!.getString(Utilll.str_language).equals("")) {
                        startActivity(
                            Intent(
                                this@IntroActivity,
                                LanguagesActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    } else {
                        startActivity(
                            Intent(
                                this@IntroActivity,
                                aaStartActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    }
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
    }


    override fun onBackPressed() {
        finishAffinity()
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