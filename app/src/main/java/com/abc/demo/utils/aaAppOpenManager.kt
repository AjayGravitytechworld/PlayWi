package com.abc.demo.utils

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import java.util.Date

class aaAppOpenManager(private val aaMyApplication: aaMyApplication) : ActivityLifecycleCallbacks, LifecycleObserver {

    private var loadTime: Long = 0
    private var currentActivity: Activity? = null
    private var loadCallback: AppOpenAdLoadCallback? = null
    private var appOpenAd: AppOpenAd? = null

    init {
        aaMyApplication.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfAvailable()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityStopped(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    private fun fetchAd() {
        if (isAdAvailable) {
            return
        }
        loadCallback = object : AppOpenAdLoadCallback() {
            override fun onAdLoaded(appOpenAd1: AppOpenAd) {
                super.onAdLoaded(appOpenAd!!)
                appOpenAd = appOpenAd1
                loadTime = Date().time
                showAdIfAvailable()
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
            }
        }
    }

    fun showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable) {
            val fullScreenContentCallback: FullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null
                        isShowingAd = false
                        //                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    startMainActivity();
//                                }
//                            }, 500);

                        //       fetchAd();
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    startMainActivity();
//                                }
//                            }, 500);
                    }

                    //                        @Override
                    //                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                    //
                    //                            new Handler().postDelayed(new Runnable() {
                    //                                @Override
                    //                                public void run() {
                    //                                    Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
                    //                                    startActivity(mainIntent);
                    //                                    finish();
                    //                                }
                    //                            }, 500);
                    //
                    //
                    //                        }
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                    }
                }

//            appOpenAd.show(MainActivity.this, fullScreenContentCallback);
            appOpenAd!!.fullScreenContentCallback = fullScreenContentCallback
            appOpenAd!!.show(currentActivity!!)
        } else {
            fetchAd()
        }
    }

    val isAdAvailable: Boolean
        get() = appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private val adRequest: AdRequest
        private get() = AdRequest.Builder().build()

    companion object {
        private const val LOG_TAG = "AppOpenManager"
        var isShowingAd = false
    }
}