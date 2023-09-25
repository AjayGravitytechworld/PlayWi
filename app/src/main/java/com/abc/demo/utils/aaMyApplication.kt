package com.abc.demo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.abc.demo.R

class aaMyApplication : MultiDexApplication() {

    var aaAppOpenMana: aaAppOpenManager? = null

    override fun onCreate() {
        super.onCreate()
        aaAppOpenMana = aaAppOpenManager(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)

            val channel = NotificationChannel(
                "CHANNEL_ID",
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}