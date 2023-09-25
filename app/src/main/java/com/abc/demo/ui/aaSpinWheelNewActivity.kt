package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivitySpinWheelNewBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.model.aaLuckyItem
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import java.util.Random

class aaSpinWheelNewActivity : AppCompatActivity() {

    var spinWheelNewBinding: ActivitySpinWheelNewBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null

    var data: ArrayList<aaLuckyItem> = ArrayList()

    var wincoin: Int? = null

    private var ii = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spinWheelNewBinding = ActivitySpinWheelNewBinding.inflate(layoutInflater)
        setContentView(spinWheelNewBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

        spinWheelNewBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        AppManage.getInstance(this).showNativeBanner(
//            spinWheelNewBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(this).showNativetype(
            spinWheelNewBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        spinWheelNewBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        if (store!!.getInt(Utilll.str_speen1Limit) < 5) {
//            spinWheelNewBinding!!.luckyWheel.visibility = View.VISIBLE
            spinWheelNewBinding!!.lblCArd.text = resources.getString(R.string.str_spinnew_text)
        } else {
//            spinWheelNewBinding!!.luckyWheel.visibility = View.GONE
            spinWheelNewBinding!!.lblCArd.text = resources.getString(R.string.str_spinnew_text1)
        }

        spinWheelNewBinding!!.lblLimit.text =
            resources.getString(R.string.str_spin_wheel) + " ( " + store!!.getInt(Utilll.str_speen1Limit) + " / 5 )"

        val aaLuckyItem1 = aaLuckyItem()
        aaLuckyItem1.topText = "100"
        aaLuckyItem1.icon = R.drawable.day1coin
        aaLuckyItem1.color = -0xc20
        data.add(aaLuckyItem1)

        val aaLuckyItem2 = aaLuckyItem()
        aaLuckyItem2.topText = "200"
        aaLuckyItem2.icon = R.drawable.day1coin
        aaLuckyItem2.color = -0x1f4e
        data.add(aaLuckyItem2)

        val aaLuckyItem3 = aaLuckyItem()
        aaLuckyItem3.topText = "50"
        aaLuckyItem3.icon = R.drawable.day1coin
        aaLuckyItem3.color = -0x3380
        data.add(aaLuckyItem3)

        val aaLuckyItem4 = aaLuckyItem()
        aaLuckyItem4.topText = "150"
        aaLuckyItem4.icon = R.drawable.coin3
        aaLuckyItem4.color = -0xc20
        data.add(aaLuckyItem4)

        val aaLuckyItem5 = aaLuckyItem()
        aaLuckyItem5.topText = "25"
        aaLuckyItem5.icon = R.drawable.cvd4f
        aaLuckyItem5.color = -0x1f4e
        data.add(aaLuckyItem5)

        val aaLuckyItem6 = aaLuckyItem()
        aaLuckyItem6.topText = "250"
        aaLuckyItem6.icon = R.drawable.day1coin
        aaLuckyItem6.color = -0x3380
        data.add(aaLuckyItem6)

        val aaLuckyItem7 = aaLuckyItem()
        aaLuckyItem7.topText = "10"
        aaLuckyItem7.icon = R.drawable.day1coin
        aaLuckyItem7.color = -0xc20
        data.add(aaLuckyItem7)

        val aaLuckyItem8 = aaLuckyItem()
        aaLuckyItem8.topText = "350"
        aaLuckyItem8.icon = R.drawable.coin_beg
        aaLuckyItem8.color = -0x1f4e
        data.add(aaLuckyItem8)

        val aaLuckyItem9 = aaLuckyItem()
        aaLuckyItem9.topText = "75"
        aaLuckyItem9.icon = R.drawable.coin1
        aaLuckyItem9.color = -0x3380
        data.add(aaLuckyItem9)

        val aaLuckyItem10 = aaLuckyItem()
        aaLuckyItem10.topText = "300"
        aaLuckyItem10.icon = R.drawable.day1coin
        aaLuckyItem10.color = -0x1f4e
        data.add(aaLuckyItem10)

        val aaLuckyItem11 = aaLuckyItem()
        aaLuckyItem11.topText = "175"
        aaLuckyItem11.icon = R.drawable.coin3
        aaLuckyItem11.color = -0x1f4e
        data.add(aaLuckyItem11)

        val aaLuckyItem12 = aaLuckyItem()
        aaLuckyItem12.topText = "225"
        aaLuckyItem12.icon = R.drawable.day1coin
        aaLuckyItem12.color = -0x1f4e
        data.add(aaLuckyItem12)

        spinWheelNewBinding!!.luckyWheel.setData(data)
        spinWheelNewBinding!!.luckyWheel.setRound(5)

        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff);
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/


        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff);
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/

        spinWheelNewBinding!!.play.setOnClickListener {
            if (store!!.getInt(Utilll.str_speen1Limit) < 5) {
                ii = 1
                play1()
                val index = getRandomIndex()
                spinWheelNewBinding!!.luckyWheel.startLuckyWheelWithTargetIndex(index)
            } else {
//                spinWheelNewBinding!!.luckyWheel.visibility = View.GONE
                spinWheelNewBinding!!.lblCArd.text = resources.getString(R.string.str_spinnew_text1)

                Toast.makeText(
                    context,
                    resources.getString(R.string.str_spinnew_text1),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        spinWheelNewBinding!!.luckyWheel.setLuckyRoundItemSelectedListener { index ->
            wincoin = data[index].topText?.toIntOrNull()
            winDialog(wincoin!!)
        }
    }

    private fun getRandomIndex(): Int {
        val rand = Random()
        return rand.nextInt(data.size - 1) + 0
    }

    private fun getRandomRound(): Int {
        val rand = Random()
        return rand.nextInt(10) + 15
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBackPressed() {
        if (ii == 0) {
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
        } else {
            Toast.makeText(
                context,
                "Please Wait",
                Toast.LENGTH_SHORT
            ).show()
        }
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
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.spin1)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun winDialog(coin: Int) {
        UtilsFiff.alertDialog(this,
            R.layout.dialog_spin, true,
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
                            Utilll.str_speen1Limit,
                            store!!.getInt(Utilll.str_speen1Limit) + 1
                        )
                        spinWheelNewBinding!!.lblLimit.text =
                            resources.getString(R.string.str_spin_wheel) + " ( " + store!!.getInt(
                                Utilll.str_speen1Limit
                            ) + " / 5 )"
                        updateCoin(coin)
                        alertDialog.dismiss()
                    }
                }
            })
    }

    private fun updateCoin(coin: Int) {
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + coin)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + coin)

        db!!.addContact(
            aaIncome(
                "Spin Wheel " + store!!.getString(Utilll.str_currentDate),
                "" + coin
            )
        )

        spinWheelNewBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        ii = 0
    }
}