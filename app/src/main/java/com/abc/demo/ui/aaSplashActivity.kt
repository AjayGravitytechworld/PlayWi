package com.abc.demo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abc.demo.R
import com.abc.demo.databinding.ActivitySplashBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.onesignal.OneSignal
import com.abc.demo.ads.ADS_SplashActivity
import com.abc.demo.ads.AppManage
import com.abc.demo.ads.getDataListner
import org.json.JSONObject
import java.util.*

@SuppressLint("CustomSplashScreen")
class aaSplashActivity : ADS_SplashActivity() {

    var splashBinding: ActivitySplashBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private val CAMERA_PERMISSION_CODE = 100
    private val STORAGE_PERMISSION_CODE = 101

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        firebaseAnalytics = Firebase.analytics

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


        if (store!!.getInt(Utilll.str_intro) == 0) {
            store!!.putString(Utilll.str_currentDate, UtilsFiff.getCurrentDateAndTime())
            store!!.putInt(Utilll.str_isSound, 1)
            store!!.putInt(Utilll.str_day, 1)
            store!!.putInt(Utilll.str_isToday, 0)
        }

        if (UtilsFiff.getCurrentDateAndTime() == store!!.getString(Utilll.str_currentDate)) {
            store!!.putInt(Utilll.str_isCurrentDate, 1)
        } else {
            store!!.putInt(Utilll.str_isCurrentDate, 0)
            store!!.putString(Utilll.str_currentDate, UtilsFiff.getCurrentDateAndTime())
        }

        if (store!!.getInt(Utilll.str_isCurrentDate) == 0) {
            store!!.putInt(Utilll.str_isDailyBonus, 0)
            store!!.putInt(Utilll.str_todayCoin, 0)
            store!!.putInt(Utilll.str_scratchLimit, 0)
            store!!.putInt(Utilll.str_speen1Limit, 0)
            store!!.putInt(Utilll.str_speen2Limit, 0)
            store!!.putInt(Utilll.str_slotLimit, 0)
            store!!.putInt(Utilll.str_giftboxLimit, 0)
            store!!.putInt(Utilll.str_checkIn, 0)
            store!!.putInt(Utilll.str_garented, 0)

            store!!.putInt(Utilll.str_day, store!!.getInt(Utilll.str_day) + 1)
            store!!.putInt(Utilll.str_isToday, 0)

            if (store!!.getInt(Utilll.str_day) == 8) {
                store!!.putInt(Utilll.str_day, 1)
                store!!.putInt(Utilll.str_day1, 0)
                store!!.putInt(Utilll.str_day2, 0)
                store!!.putInt(Utilll.str_day3, 0)
                store!!.putInt(Utilll.str_day4, 0)
                store!!.putInt(Utilll.str_day5, 0)
                store!!.putInt(Utilll.str_day6, 0)
                store!!.putInt(Utilll.str_day7, 0)
            }
        }

        UtilsFiff.sound = store!!.getInt(Utilll.str_isSound)

        if (ContextCompat.checkSelfPermission(
                this@aaSplashActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
        }
        checkPermission(
            Manifest.permission.POST_NOTIFICATIONS,
            STORAGE_PERMISSION_CODE
        )

        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, 0)
        }

        callAppManager()
    }

    override fun onBackPressed() {
    }

    fun callAppManager() {
        //        if (!isDevMode()) {
        ADSinit(this@aaSplashActivity, currentVersionCode, object : getDataListner {
            override fun onSuccess() {
                AppManage.getInstance(context).loadInterstitialAd(this@aaSplashActivity)

//                OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE)
                OneSignal.initWithContext(this@aaSplashActivity, AppManage.onesignal)

                UtilsFiff.loadGameData1(context!!)
                UtilsFiff.loadGameData2(context!!)
                UtilsFiff.loadGameData3(context!!)
                UtilsFiff.loadGameData4(context!!)
                UtilsFiff.loadGameData5(context!!)
                UtilsFiff.loadLeaderBoard(context!!)

                if (store!!.getInt(Utilll.str_intro) == 0) {
                    startActivity(
                        Intent(
                            this@aaSplashActivity, IntroActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                } else if (store!!.getInt(Utilll.str_isLogin) == 0) {
                    startActivity(
                        Intent(
                            this@aaSplashActivity, aaLoginActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                } else if (store!!.getString(Utilll.str_language).equals("")) {
                    startActivity(
                        Intent(
                            this@aaSplashActivity, LanguagesActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                } else {
                    startActivity(
                        Intent(
                            this@aaSplashActivity, aaStartActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }
            }

            override fun onUpdate(url: String) {
                showUpdateDialog(url)
            }

            override fun onRedirect(url: String) {
                showRedirectDialog(url)
            }

            override fun onReload() {
                startActivity(Intent(this@aaSplashActivity, aaSplashActivity::class.java))
                finish()
            }

            override fun onGetExtradata(extraData: JSONObject) {}
        })
//        } else {
//            developerDialog()
//        }
    }

    @TargetApi(17)
    fun isDevMode(): Boolean {
        return if (Integer.valueOf(Build.VERSION.SDK) == 16) {
            Settings.Secure.getInt(
                applicationContext.contentResolver, Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0
            ) != 0
        } else if (Integer.valueOf(Build.VERSION.SDK) >= 17) {
            Settings.Secure.getInt(
                applicationContext.contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
            ) != 0
        } else false
    }

    private fun developerDialog() {
        UtilsFiff.alertDialog(this@aaSplashActivity,
            R.layout.dialog_devmode,
            false,
            object : UtilsFiff.DialogListener {

                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog?.findViewById<View>(R.id.lbl4) as TextView).setOnClickListener { alertDialog.dismiss() }
                    (alertDialog.findViewById<View>(R.id.lbl5) as TextView).setOnClickListener {
                        startActivity(Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
                    }
                }
            })
    }

    @SuppressLint("SetTextI18n")
    fun showRedirectDialog(url: String?) {
        val dialog = Dialog(this@aaSplashActivity)
        dialog.setCancelable(false)
        val view: View =
            layoutInflater.inflate(R.layout.installnewappdialog, null)
        dialog.setContentView(view)
        val update: TextView = view.findViewById(R.id.update)
        val txt_title: TextView = view.findViewById(R.id.txt_title)
        val txt_decription: TextView = view.findViewById(R.id.txt_decription)
        update.text = "Install Now"
        txt_title.text = "Install our new app now and enjoy"
        txt_decription.text =
            "We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app."
        update.setOnClickListener(View.OnClickListener {
            try {
                val marketUri = Uri.parse(url)
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            } catch (ignored1: ActivityNotFoundException) {
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create()
        }
        dialog.show()
        val window = dialog.window
        window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("SetTextI18n")
    fun showUpdateDialog(url: String?) {
        val dialog = Dialog(this@aaSplashActivity)
        dialog.setCancelable(false)
        val view: View =
            layoutInflater.inflate(R.layout.installnewappdialog, null)
        dialog.setContentView(view)
        val update: TextView = view.findViewById(R.id.update)
        val txt_title: TextView = view.findViewById(R.id.txt_title)
        val txt_decription: TextView = view.findViewById(R.id.txt_decription)
        update.text = "Update Now"
        txt_title.text = "Update our new app now and enjoy"
        txt_decription.text = ""
        txt_decription.visibility = View.GONE
        update.setOnClickListener(View.OnClickListener { _: View? ->
            try {
                val marketUri = Uri.parse(url)
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            } catch (ignored1: ActivityNotFoundException) {
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create()
        }
        dialog.show()
        val window = dialog.window
        window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private val currentVersionCode: Int
        get() {
            val manager: PackageManager = packageManager
            var info: PackageInfo? = null
            try {
                info = manager.getPackageInfo(packageName, 0)
                return info.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return 0
        }


    fun checkPermission(permission: String, requestCode: Int) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                this@aaSplashActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@aaSplashActivity,
                arrayOf(permission),
                requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            // Checking whether user granted the permission or not.
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Showing the toast message
            } else {
                Toast.makeText(
                    this@aaSplashActivity,
                    "You Need To Allow Permission",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(
                    this@aaSplashActivity,
                    "You Need To Allow Permission",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }else{
//            callAppManager()
        }
    }
}