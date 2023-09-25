package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.SpinwheelBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import java.util.Random

class aaSpinWheelActivity : AppCompatActivity() {

    private var spinWheelBinding: SpinwheelBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spinWheelBinding = SpinwheelBinding.inflate(layoutInflater)
        setContentView(spinWheelBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

//        AppManage.getInstance(this).showNativeBanner(
//            spinWheelBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(this).showNativetype(
            spinWheelBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        spinWheelBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        spinWheelBinding!!.lblLimit.text =
            resources.getString(R.string.str_spinbtn) + " ( " + store!!.getInt(Utilll.str_speen2Limit) + " / 5 )"

        if (store!!.getInt(Utilll.str_speen2Limit) < 5) {
//            spinWheelBinding!!.layoutSpin.visibility = View.VISIBLE
            spinWheelBinding!!.lblCArd.text = resources.getString(R.string.str_spinnew1_text)
        } else {
//            spinWheelBinding!!.layoutSpin.visibility = View.GONE
            spinWheelBinding!!.lblCArd.text = resources.getString(R.string.str_spinnew1_text1)
        }

        spinWheelBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        spinWheelBinding!!.btnAction.setOnClickListener {
            if (store!!.getInt(Utilll.str_speen2Limit) < 5) {
                if (!spinWheelBinding!!.luckyPanel.isGameRunning) {
                    play1()
                    spinWheelBinding!!.luckyPanel.startGame()
                    val handler = Handler()
                    handler.postDelayed({
                        val stayIndex: Int = Random().nextInt(8)
                        spinWheelBinding!!.luckyPanel.tryToStop(stayIndex)
                        updateCoin(stayIndex)
                    }, 5000)
                }
            } else {
                Toast.makeText(this, "Your Daily Spin Limit is Over", Toast.LENGTH_SHORT).show()
            }
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

    private fun updateCoin(coin: Int) {
        when (coin) {
            0 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 20)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 20)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "20"
                    )
                )
            }

            1 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 30)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 30)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "30"
                    )
                )
            }

            2 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 35)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 35)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "35"
                    )
                )
            }

            3 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 40)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 40)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "40"
                    )
                )
            }

            4 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 500)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 500)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "500"
                    )
                )
            }

            5 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 100)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 100)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "100"
                    )
                )
            }

            6 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 100)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 100)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "100"
                    )
                )
            }

            7 -> {
                store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 40)
                store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 40)

                db!!.addContact(
                    aaIncome(
                        "Fortune Wheel " + store!!.getString(Utilll.str_currentDate),
                        "40"
                    )
                )
            }
        }
        spinWheelBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        store!!.putInt(
            Utilll.str_speen2Limit,
            store!!.getInt(Utilll.str_speen2Limit) + 1
        )
        spinWheelBinding!!.lblLimit.text =
            resources.getString(R.string.str_spinbtn) + " ( " + store!!.getInt(Utilll.str_speen2Limit) + " / 5 )"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play1() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.squarespin)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }
}