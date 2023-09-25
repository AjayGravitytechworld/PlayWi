package com.abc.demo.utils

import android.content.Context
import android.content.SharedPreferences

class Utilll private constructor(context: Context) {

    private val sharedPrefrancee: SharedPreferences

    fun clearData() {
        val editor = sharedPrefrancee.edit()
//        store!!.putInt(login_str, 0)
//        editor.putString(userid_str, "")
//        editor.putString(username_str, "")
//        editor.putString(userpic_str, "")
//        editor.putString(useremail_str, "")
//        editor.putString(usernumber_str, "")
//        editor.putString(deviceid_str, "")
//        editor.putString(businessid_str, "")
//        editor.putString(business_str, "")
//        editor.putString(image_str, "")
//        editor.putString(businessname_str, "")
//        editor.putString(ownername_str, "")
//        editor.putString(mobile_str, "")
//        editor.putString(email_str, "")
//        editor.putString(website_str, "")
//        editor.putString(address_str, "")
//        editor.putString(state_str, "")
        editor.apply()
    }

    fun putString(key: String?, value: String?) {
        val editor = sharedPrefrancee.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return sharedPrefrancee.getString(key, "")
    }

    fun putBoolean(key: String?, value: Boolean?) {
        val editor = sharedPrefrancee.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPrefrancee.getBoolean(key, false)
    }

    fun putInt(key: String?, num: Int) {
        val editor = sharedPrefrancee.edit()
        editor.putInt(key, num)
        editor.apply()
    }

    fun getInt(key: String?): Int {
        return sharedPrefrancee.getInt(key, 0)
    }

    fun clear() {
        val editor = sharedPrefrancee.edit()
        editor.clear()
        editor.apply()
    }

    fun remove() {
        val editor = sharedPrefrancee.edit()
        //        editor.remove(filename);
        editor.apply()
    }

    init {
        sharedPrefrancee = context.applicationContext.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private var store: Utilll? = null

        var str_intro = "INTRO"
        var str_language = "LAMGUAGE"
        var str_isLogin = "ISLOGIN"

        var str_userName = "USERNAME"
        var str_userImage = "USERIMAGE"
        var str_userEmail = "USEREMAIL"
        var str_deviceId = "DEVICEID"

        var str_totalCoin = "TOTALCOIN"
        var str_withdrowCoin = "WITHDROWCOIN"
        var str_todayCoin = "TODAYCOIN"
        var str_scratchLimit = "scratchLimit"
        var str_speen1Limit = "speen1Limit"
        var str_speen2Limit = "speen2Limit"
        var str_slotLimit = "SLOTLIMIT"
        var str_giftboxLimit = "GIFTBOXLIMIT"

        var str_isWelcomeBonus = "ISWELCOMEBONUS"
        var str_isLoginBonus = "ISLOGINBONUS"
        var str_isDailyBonus = "ISDAILYBONUS"
        var str_isCurrentDate = "ISCURRENTDATE"
        var str_isfirstInvite = "ISFIRSTINVITE"
        var str_isSound = "ISSOUND"
        var str_isNotification = "ISNOTIFICATION"

        var str_isToday = "ISTODAY"
        var str_day = "DAY"
        var str_day1 = "DAY1"
        var str_day2 = "DAY2"
        var str_day3 = "DAY3"
        var str_day4 = "DAY4"
        var str_day5 = "DAY5"
        var str_day6 = "DAY6"
        var str_day7 = "DAY7"
        var str_currentDate = "CURRENTDATE"
        var str_checkIn = "CHECKIN"
        var str_garented = "GARENTED"

        fun getInstance(context: Context): Utilll? {
            if (store == null) {
                store = Utilll(context)
            }
            return store
        }
    }
}