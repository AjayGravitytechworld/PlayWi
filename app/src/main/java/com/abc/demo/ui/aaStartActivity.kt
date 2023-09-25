package com.abc.demo.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.databinding.ActivityStartBinding
import com.abc.demo.model.aaTaskModel
import com.abc.demo.model.aaVideoModel
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import org.json.JSONObject
import java.util.Locale

class aaStartActivity : AppCompatActivity() {

    private var startBinding: ActivityStartBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(startBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        getData()

        val language = store!!.getString(Utilll.str_language)

        if (language == "") {
            UtilsFiff.setLanguage(context!!, "en")
            val configuration = Configuration()
            configuration.locale = Locale.forLanguageTag("en")
            onConfigurationChanged(configuration)
            UtilsFiff.setLanguage(this, "en")
        } else {
            UtilsFiff.setLanguage(context!!, language)
            val configuration = Configuration()
            configuration.locale = Locale.forLanguageTag(language)
            onConfigurationChanged(configuration)
            UtilsFiff.setLanguage(this, language)
        }

        val anim = startBinding!!.container.background as AnimationDrawable
        anim.setEnterFadeDuration(100)
        anim.setExitFadeDuration(100)
        anim.start()

        AppManage.getInstance(context).showNativetype(
            startBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        startBinding!!.startbtn.setOnClickListener {
            vibratee1()
            play()
//            AppManage.getInstance(this@StartActivity).showInterstitialAd(
//                this@StartActivity,
//                {
            startActivity(Intent(this@aaStartActivity, aaHomeActivity::class.java))
            Animatoo.animateZoom(context!!)
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }
        startBinding!!.sharebtn.setOnClickListener {
            vibratee()
            play()
            UtilsFiff.shareApp(context!!)
        }
        startBinding!!.privecybtn.setOnClickListener {
            vibratee()
            play()
            UtilsFiff.openGame(context!!, AppManage.app_privacyPolicyLink)
        }
        startBinding!!.ratebtn.setOnClickListener {
            vibratee()
            play()
            UtilsFiff.rateApp(context!!)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBackPressed() {
        vibratee()
        play()
        exitDialog()
    }

    private fun exitDialog() {
        UtilsFiff.alertDialog(
            this@aaStartActivity,
            R.layout.dialog_exit,
            true,
            object : UtilsFiff.DialogListener {

                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
//                    AppManage.getInstance(context).showNativeSmall((alertDialog?.findViewById<View>(R.id.nativeAd) as CircularRevealLinearLayout), AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes")

                    if (alertDialog != null) {
                        (alertDialog.findViewById<View>(R.id.lbl4) as TextView).setOnClickListener {
                            vibratee()
                            play()
                            finish()
                            finishAffinity()
                        }
                    }
                    if (alertDialog != null) {
                        (alertDialog.findViewById<View>(R.id.lbl5) as TextView).setOnClickListener {
                            vibratee()
                            play()
                            alertDialog.dismiss()
                        }
                    }
                }
            })
    }

    private fun play() {
        mediaPlayer = MediaPlayer.create(this, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun getData() {
        val url = UtilsFiff.myapi
        val mRequestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null,
                { response ->
                    for (i in 0 until response!!.length()) {
                        UtilsFiff.trending_video!!.add(
                            aaVideoModel(
                                response.getString(i),
                                "",
                                response.getString(i)
                            )
                        )
                    }
                    getData1()
                }
            ) { }
        mRequestQueue.add(jsonArrayRequest)
    }

    private fun getData1() {
        val url = UtilsFiff.myapi
        val mRequestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null,
                { response ->
                    for (i in 0 until response!!.length()) {
                        UtilsFiff.popular_video!!.add(
                            aaVideoModel(
                                response.getString(i),
                                "",
                                response.getString(i)
                            )
                        )
                    }
                    getData2()
                }
            ) { }
        mRequestQueue.add(jsonArrayRequest)
    }

    private fun getData2() {
        val url = UtilsFiff.myapi
        val mRequestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null,
                { response ->
                    for (i in 0 until response!!.length()) {
                        UtilsFiff.today_video!!.add(
                            aaVideoModel(
                                response.getString(i),
                                "",
                                response.getString(i)
                            )
                        )
                    }
                    getTaskData()
                }
            ) { }
        mRequestQueue.add(jsonArrayRequest)
    }

    private fun getTaskData() {
        val url = UtilsFiff.applist
        val mRequestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null,
                { response ->
                    for (i in 0 until response!!.length()) {
                        val jsonObject: JSONObject = response.getJSONObject(i)
                        UtilsFiff.task_iteams!!.add(
                            aaTaskModel(
                                jsonObject.getString("app_name"),
                                jsonObject.getString("app_logo"),
                                jsonObject.getString("app_link"),
                                jsonObject.getInt("coin")
                            )
                        )
                    }
                }
            ) { }
        mRequestQueue.add(jsonArrayRequest)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun vibratee() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }
    }

    private fun vibratee1() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(
                    500, 255
                )
            )
        } else {
            vibrator!!.vibrate(500)
        }
    }
}