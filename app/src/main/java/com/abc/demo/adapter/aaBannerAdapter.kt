package com.abc.demo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.abc.demo.R
import com.abc.demo.utils.UtilsFiff
import com.bumptech.glide.Glide

class aaBannerAdapter(private val context: Context) : PagerAdapter() {

    private var mediaPlayer: MediaPlayer? = null
    private val fruits = arrayOf(R.drawable.gg1, R.drawable.gg2, R.drawable.gg3, R.drawable.gg4)

    override fun getCount(): Int {
        return fruits.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val imgBanner: ImageView
        val view: View = layoutInflater.inflate(R.layout.item_banner, null)
        imgBanner = view.findViewById<ImageView>(R.id.imgBanner)
        Glide.with(context).load(fruits[position]).into(imgBanner)
        imgBanner.setOnClickListener {
            play()
            if(position == 3){
                UtilsFiff.shareApp(context)
            }else{
                UtilsFiff.customAdCLick(context)
            }
//            UtilsFile.customAdCLick(context)
        }
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(context, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }
}