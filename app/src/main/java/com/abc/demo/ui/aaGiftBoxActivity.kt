package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivityGiftBoxBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import tyrantgit.explosionfield.ExplosionField

class aaGiftBoxActivity : AppCompatActivity() {

    private var giftBoxBinding: ActivityGiftBoxBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null
    private var mExplosionField: ExplosionField? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        giftBoxBinding = ActivityGiftBoxBinding.inflate(layoutInflater)
        setContentView(giftBoxBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

        giftBoxBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        AppManage.getInstance(this).showNativeBanner(
//            giftBoxBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

//        AppManage.getInstance(this).showNative(
//            giftBoxBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        AppManage.getInstance(this).showNativetype(
            giftBoxBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        giftBoxBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        giftBoxBinding!!.lblLimit.text =
            resources.getString(R.string.str_scratch_card) + " ( " + store!!.getInt(Utilll.str_giftboxLimit) + " / 5 )"

        if (store!!.getInt(Utilll.str_giftboxLimit) < 5) {
            giftBoxBinding!!.imgGift.visibility = View.VISIBLE
//            giftBoxBinding!!.lblCArd.visibility = View.GONE
            giftBoxBinding!!.lblCArd.text = "Tap on Prize to take your Gift"
        } else {
            giftBoxBinding!!.imgGift.visibility = View.GONE
//            giftBoxBinding!!.lblCArd.visibility = View.VISIBLE
            giftBoxBinding!!.lblCArd.text = resources.getString(R.string.text_gamee123)
        }

        mExplosionField = ExplosionField.attach2Window(this)

        giftBoxBinding!!.imgGift.setOnClickListener {
            play1()
            mExplosionField!!.explode(giftBoxBinding!!.imgGift)
            winDialog(100)
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

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play1() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(
                    400, 255
                )
            )
        } else {
            vibrator!!.vibrate(400)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.coin1)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun winDialog(coin: Int) {
        UtilsFiff.alertDialog(this,
            R.layout.dialog_gift, true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.tex6) as TextView).text = " $coin Coins"
                    (alertDialog.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        play()
                        store!!.putInt(
                            Utilll.str_giftboxLimit,
                            store!!.getInt(Utilll.str_giftboxLimit) + 1
                        )
//                        giftBoxBinding!!.lblLimit.text =
//                            resources.getString(R.string.str_scratch_card) + " ( " + store!!.getInt(Keystore.str_speen1Limit) + " / 5 )"
                        updateCoin(coin)
                        alertDialog.dismiss()
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            })
    }

    private fun updateCoin(coin: Int) {
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + coin)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + coin)

        db!!.addContact(
            aaIncome(
                "Gift Box " + store!!.getString(Utilll.str_currentDate),
                "" + coin
            )
        )

        giftBoxBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        giftBoxBinding!!.lblLimit.text =
            resources.getString(R.string.str_scratch_card) + " ( " + store!!.getInt(Utilll.str_giftboxLimit) + " / 5 )"

        if (store!!.getInt(Utilll.str_giftboxLimit) < 5) {
            giftBoxBinding!!.imgGift.visibility = View.VISIBLE
//            giftBoxBinding!!.lblCArd.visibility = View.GONE
            giftBoxBinding!!.lblCArd.text = "Tap on Prize to take your Gift"
        } else {
            giftBoxBinding!!.imgGift.visibility = View.GONE
//            giftBoxBinding!!.lblCArd.visibility = View.VISIBLE
            giftBoxBinding!!.lblCArd.text = resources.getString(R.string.text_gamee123)
        }
    }
}