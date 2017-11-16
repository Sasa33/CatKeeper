package com.tw.training.catkeeper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.BannerAdapter
import com.tw.training.catkeeper.fragment.MyCatFragment
import com.tw.training.catkeeper.fragment.NearbyCatFragment

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var mViewPager: ViewPager
    private lateinit var mIndicatorView: ViewGroup
    private lateinit var mLeftTab: View
    private lateinit var mRightTab: View

    private var mPreviousPosition: Int = 0

    private val mImageResIds = arrayListOf<Int>(R.mipmap.banner_icon_1, R.mipmap.banner_icon_2,
            R.mipmap.banner_icon_3, R.mipmap.banner_icon_4)

    private var mHandler: Handler = Handler()
    private var mRunnable = object: Runnable {
        override fun run() {
            mViewPager.currentItem++
            mHandler.postDelayed(this, 5000)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBanner()

        initTab()

        setupFragment()
    }

    override fun onResume() {
        super.onResume()
        mHandler.post(mRunnable)
    }

    private fun initTab() {
        mLeftTab = findViewById(R.id.left_tab_btn)
        mRightTab = findViewById(R.id.right_tab_btn)

        mLeftTab.isEnabled = false
        mRightTab.isEnabled = true

        mLeftTab.setOnClickListener {
            switchToNearbyCats()
        }

        mRightTab.setOnClickListener {
            switchToMyCat()
        }
    }

    private fun initBanner() {
        mViewPager = findViewById(R.id.viewpager)
        mIndicatorView = findViewById(R.id.indicator_view)

        var imageList = ArrayList<ImageView>()
//        mImageResIds.forEach { id ->
//            val iv = ImageView(this)
//            iv.scaleType = ImageView.ScaleType.FIT_XY
//            iv.setImageResource(id)
//        }

        mImageResIds.forEach {
            val iv = ImageView(this)
            iv.scaleType = ImageView.ScaleType.FIT_XY
            iv.setImageResource(it)
            imageList.add(iv)
        }

        mViewPager.adapter = BannerAdapter(imageList)
        mViewPager.addOnPageChangeListener(this)
        mPreviousPosition = mViewPager.currentItem

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        updateIndicator(position)
        mPreviousPosition = position
    }

    private fun updateIndicator(position: Int) {
        mIndicatorView.getChildAt(position)
                .setBackgroundResource(R.drawable.banner_indicator_selected)
        mIndicatorView.getChildAt(mPreviousPosition)
                .setBackgroundResource(R.drawable.banner_indicator_unselected)
    }


    private lateinit var nearbyCatFragment: NearbyCatFragment
    private lateinit var myCatFragment: MyCatFragment

    private fun setupFragment() {
        nearbyCatFragment = NearbyCatFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, nearbyCatFragment)
        transaction.commit()
    }

    private fun switchToMyCat() {
        mLeftTab.isEnabled = true
        mRightTab.isEnabled = false
        myCatFragment = MyCatFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, myCatFragment)
        transaction.commit()
    }

    private fun switchToNearbyCats() {
        mLeftTab.isEnabled = false
        mRightTab.isEnabled = true
        nearbyCatFragment = NearbyCatFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, nearbyCatFragment)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }
}
