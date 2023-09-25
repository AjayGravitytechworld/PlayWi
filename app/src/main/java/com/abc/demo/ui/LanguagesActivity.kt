package com.abc.demo.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.abc.demo.R
import com.abc.demo.databinding.ActivityLanguagesBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import java.util.Locale

class LanguagesActivity : AppCompatActivity() {

    private var languagesBinding: ActivityLanguagesBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    var i = 0

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languagesBinding = ActivityLanguagesBinding.inflate(layoutInflater)
        setContentView(languagesBinding?.root)

        context = this
        store = Utilll.getInstance(this)


        AppManage.getInstance(this).showNativetype(
            languagesBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 2
        )

        when (store!!.getString(Utilll.str_language)) {
            "en" -> {
                setbg(1)
            }

            "hi" -> {
                setbg(2)
            }

            "es" -> {
                setbg(3)
            }

            "pt" -> {
                setbg(4)
            }

            "ko" -> {
                setbg(5)
            }

            "in" -> {
                setbg(6)
            }
        }

        languagesBinding!!.english.setOnClickListener {
            play()
            setbg(1)
        }

        languagesBinding!!.hindi.setOnClickListener {
            play()
            setbg(2)
        }

        languagesBinding!!.spanish.setOnClickListener {
            play()
            setbg(3)
        }

        languagesBinding!!.portuguese.setOnClickListener {
            play()
            setbg(4)
        }

        languagesBinding!!.korean.setOnClickListener {
            play()
            setbg(5)
        }

        languagesBinding!!.indonesia.setOnClickListener {
            play()
            setbg(6)
        }

        languagesBinding!!.selectBtn1.setOnClickListener {
            play()
            if (i == 0) {
                Toast.makeText(context, "Please Select Language", Toast.LENGTH_SHORT).show()
            } else {
                var lang = ""
                when (i) {
                    1 -> {
                        lang = "en"
                        store!!.putString(Utilll.str_language, "en")
                        UtilsFiff.setLanguage(this, "en")
                    }

                    2 -> {
                        lang = "hi"
                        store!!.putString(Utilll.str_language, "hi")
                        UtilsFiff.setLanguage(this, "hi")
                    }

                    3 -> {
                        lang = "es"
                        store!!.putString(Utilll.str_language, "es")
                        UtilsFiff.setLanguage(this, "es")
                    }

                    4 -> {
                        lang = "pt"
                        store!!.putString(Utilll.str_language, "pt")
                        UtilsFiff.setLanguage(this, "pt")
                    }

                    5 -> {
                        lang = "ko"
                        store!!.putString(Utilll.str_language, "ko")
                        UtilsFiff.setLanguage(this, "ko")
                    }

                    6 -> {
                        lang = "in"
                        store!!.putString(Utilll.str_language, "in")
                        UtilsFiff.setLanguage(this, "in")
                    }
                }
                val configuration = Configuration()
                configuration.locale = Locale.forLanguageTag(lang)
                onConfigurationChanged(configuration)

                AppManage.getInstance(this@LanguagesActivity).showInterstitialAd(
                    this@LanguagesActivity,
                    {
                        store!!.putInt(Utilll.str_intro, 1)
                        startActivity(
                            Intent(this@LanguagesActivity, aaStartActivity::class.java).setFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                            )
                        )
                    },
                    "",
                    AppManage.app_mainClickCntSwAd
                )
            }
        }
    }

    private fun setbg(ii: Int) {
        when (ii) {
            1 -> {
                i = 1
                languagesBinding!!.english.setBackgroundResource(R.drawable.sellang)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.taskbtnbg)
            }

            2 -> {
                i = 2
                languagesBinding!!.english.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.sellang)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.taskbtnbg)
            }

            3 -> {
                i = 3
                languagesBinding!!.english.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.sellang)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.taskbtnbg)
            }

            4 -> {
                i = 4
                languagesBinding!!.english.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.sellang)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.taskbtnbg)
            }

            5 -> {
                i = 5
                languagesBinding!!.english.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.sellang)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.taskbtnbg)
            }

            6 -> {
                i = 6
                languagesBinding!!.english.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.hindi.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.spanish.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.portuguese.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.korean.setBackgroundResource(R.drawable.taskbtnbg)
                languagesBinding!!.indonesia.setBackgroundResource(R.drawable.sellang)
            }
        }
    }

    @Deprecated("Deprecated in Java")
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