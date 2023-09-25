package com.abc.demo.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.DecimalFormat
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.databinding.ActivitySettingBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import java.io.File

class aaSettingActivity : AppCompatActivity() {
    private var settingBinding: ActivitySettingBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var sound: Int? = null
    private var notification: Int? = null

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(settingBinding!!.root)

        context = this
        store = Utilll.getInstance(this)

        AppManage.getInstance(context).showNativetype(
            settingBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(this).showNative(
//            settingBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        when (store!!.getString(Utilll.str_language)) {
            "en" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_english)
            }

            "hi" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_hindi)
            }

            "es" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_spanish)
            }

            "pt" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_portuguese)
            }

            "ko" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_Korean)
            }

            "in" -> {
                settingBinding!!.lblLanguage.text = resources.getString(R.string.str_indonesia)
            }
        }

        settingBinding!!.rupees.text = "" + store!!.getInt(Utilll.str_totalCoin)
        settingBinding!!.noMb.text = "" + formatFileSize(initializeCache())
        settingBinding!!.updateTxt.text = "" + getVersionCode()

        settingBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        AppManage.getInstance(this).showNativeBanner(
//            settingBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        settingBinding!!.rewards.setOnClickListener {
            play()
//            AppManage.getInstance(this@SettingActivity).showInterstitialAd(
//                this@SettingActivity,
//                {
                    startActivity(Intent(this@aaSettingActivity, aaRedeemActivity::class.java))
                    Animatoo.animateZoom(context!!)
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        settingBinding!!.language.setOnClickListener {
            play()
            AppManage.getInstance(this@aaSettingActivity).showInterstitialAd(
                this@aaSettingActivity,
                {
                    startActivity(Intent(this@aaSettingActivity, LanguagesActivity::class.java))
                    Animatoo.animateZoom(context!!)
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        settingBinding!!.clearCache.setOnClickListener {
            play()
            deleteCache(context!!)
        }

        settingBinding!!.checkUpdate.setOnClickListener {
            play()
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }
        settingBinding!!.cloudService.setOnClickListener {
            play()
//            AppManage.getInstance(this@SettingActivity).showInterstitialAd(
//                this@SettingActivity,
//                {
//                    startActivity(Intent(this@SettingActivity, HomeActivity::class.java))
//                    Animatoo.animateZoom(context)
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        sound = store!!.getInt(Utilll.str_isSound)
        notification = store!!.getInt(Utilll.str_isNotification)

        if (sound == 0) {
            settingBinding!!.switch1.setImageResource(R.drawable.switch_off)
        } else {
            settingBinding!!.switch1.setImageResource(R.drawable.switch_on)
        }

        if (notification == 0) {
            settingBinding!!.switch2.setImageResource(R.drawable.switch_off)
        } else {
            settingBinding!!.switch2.setImageResource(R.drawable.switch_on)
        }

        settingBinding!!.switch1.setOnClickListener {
            play()
            if (sound == 0) {
                sound = 1
                settingBinding!!.switch1.setImageResource(R.drawable.switch_on)
                store!!.putInt(Utilll.str_isSound, sound!!)
                UtilsFiff.sound = 1
            } else {
                sound = 0
                settingBinding!!.switch1.setImageResource(R.drawable.switch_off)
                store!!.putInt(Utilll.str_isSound, sound!!)
                UtilsFiff.sound = 0
            }
        }

        settingBinding!!.switch2.setOnClickListener {
            play()
            if (notification == 0) {
                notification = 1
                settingBinding!!.switch2.setImageResource(R.drawable.switch_on)
                store!!.putInt(Utilll.str_isNotification, notification!!)
            } else {
                notification = 0
                settingBinding!!.switch2.setImageResource(R.drawable.switch_off)
                store!!.putInt(Utilll.str_isNotification, notification!!)
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

    private fun initializeCache(): Long {
        var size: Long = 0
        size += getDirSize(this.cacheDir)
        size += getDirSize(this.externalCacheDir)
        return size
    }

    private fun getDirSize(dir: File?): Long {
        var size: Long = 0
        for (file in dir!!.listFiles()) {
            if (file != null && file.isDirectory) {
                size += getDirSize(file)
            } else if (file != null && file.isFile) {
                size += file.length()
            }
        }
        return size
    }

    private fun formatFileSize(size: Long): String? {
        var hrSize: String? = null
        val b = size.toDouble()
        val k = size / 1024.0
        val m = size / 1024.0 / 1024.0
        val g = size / 1024.0 / 1024.0 / 1024.0
        val t = size / 1024.0 / 1024.0 / 1024.0 / 1024.0
        val dec = DecimalFormat("0.00")
        hrSize = if (t > 1) {
            dec.format(t) + " TB"
        } else if (g > 1) {
            dec.format(g) + " GB"
        } else if (m > 1) {
            dec.format(m) + " MB"
        } else if (k > 1) {
            dec.format(k) + " KB"
        } else {
            dec.format(b) + " Bytes"
        }
        return hrSize
    }

    private fun deleteCache(context: Context) {
        try {
            val dir = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }

    private fun getVersionCode(): String {
        return try {
            val pInfo = context!!.packageManager.getPackageInfo(
                context!!.packageName, 0
            )
            val version = pInfo.versionName
            version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
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

        if (UtilsFiff.sound == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.click)
            mediaPlayer!!.start()
        }
    }
}