package com.luis.android.app.business.fleet.view.adapter

import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.luis.android.app.business.fleet.R
import java.util.*

class DotsSliderAdapter(val linearLayout: LinearLayout, var listSize: Int, val viewPager: ViewPager, val context: Context) {

    private var mDotscount = 0
    private lateinit var mDots: Array<ImageView>
    private lateinit var mTimer: Timer
    private val TIME_DELAY_MS: Long = 1500
    private val TIME_PERIOD_MS: Long = 5000
    private var mCurrentPage = 0

    fun resetDotsAnim(){
        listSize = 0
        mDotscount = 0
        mDots = emptyArray()
        linearLayout.removeAllViews()

        mTimer.let{
            it.cancel()
            Log.e("TimerCancelled", "true")
        }
    }

    fun initSliderWithTimer(){
        val handler = Handler()
        val updateHandler = Runnable {
            if (mCurrentPage == listSize) {
                mCurrentPage = 0
            }
            viewPager.setCurrentItem(mCurrentPage++, true)
        }

        mTimer = Timer()
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(updateHandler)
            }
        }, TIME_DELAY_MS, TIME_PERIOD_MS)

        initSlider()
    }

    fun initSlider(){
        mDotscount = viewPager.adapter!!.count
        mDots = Array(mDotscount){ i -> ImageView(context) }

        for (i in 0 until mDotscount) {
            mDots[i] = ImageView(context)
            mDots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 2, 8, 0)
            linearLayout.addView(mDots[i], params)
        }
        mDots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot))

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in 0 until mDotscount) {
                    mDots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot))
                }
                mDots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun nextPage() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    fun lastPage() {
        viewPager.currentItem = viewPager.currentItem - 1
    }
}