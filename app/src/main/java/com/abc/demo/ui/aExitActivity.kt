package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivityExitBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import java.util.Timer
import java.util.TimerTask

class aExitActivity : AppCompatActivity() {

    private var exitBinding: ActivityExitBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null
    private var i = 0

    var type = 0
    private var lnl = ""

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitBinding = ActivityExitBinding.inflate(layoutInflater)
        setContentView(exitBinding?.root)

        type = intent.getIntExtra("type",0)

        lnl = if(type == 1){
            AppManage.link1
        }else{
            AppManage.link2
        }

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

        var time = 30
//        val handler = Handler()
//        val runnable = Runnable {
//            time--
//            if (time == 1) {
//                exitBinding!!.btnBack.visibility = View.VISIBLE
//                exitBinding!!.tvProfile1.visibility = View.GONE
//                i = 1
//            } else {
//                exitBinding!!.tvProfile1.text = "" + time
//            }
//        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    //this will run on UI thread so you can update views here
                    time--
                    if (time == 1) {
                        exitBinding!!.btnBack.visibility = View.VISIBLE
                        exitBinding!!.tvProfile1.visibility = View.GONE
                        i = 1

                        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 50)
                        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 50)

                        db!!.addContact(
                            aaIncome(
                                "Gift Box " + store!!.getString(Utilll.str_currentDate),
                                "" + 50
                            )
                        )
                    } else {
                        exitBinding!!.tvProfile1.text = "" + time
                    }
                }
            }
        }, 1000, 1000)

//        handler.postDelayed({
//            exitBinding!!.btnBack.visibility = View.VISIBLE
//            i = 1
//        }, 60000)

//        handler.postDelayed({
//            runnable
//        }, 60000)

        AppManage.getInstance(this).showNativetype(
            exitBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 3
        )

//        exitBinding!!.web1.settings.javaScriptEnabled = true
//        exitBinding!!.web1.loadUrl(AppManage.Qureka)

        exitBinding!!.web1.webViewClient = WebViewController(lnl)
        exitBinding!!.web1.clearHistory()
        exitBinding!!.web1.settings.javaScriptEnabled = true
        exitBinding!!.web1.settings.javaScriptCanOpenWindowsAutomatically = true
        exitBinding!!.web1.settings.domStorageEnabled = true
        exitBinding!!.web1.loadUrl(lnl)
        exitBinding!!.web1.requestFocus()


        exitBinding!!.btnBack.setOnClickListener { onBackPressed() }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBackPressed() {
        if (i == 1) {
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
            Toast.makeText(context, "You can not back wait one minute.", Toast.LENGTH_SHORT).show()
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

    class WebViewController(private val lnl: String) : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            view.loadUrl(lnl)
            return true
        }
    }
}