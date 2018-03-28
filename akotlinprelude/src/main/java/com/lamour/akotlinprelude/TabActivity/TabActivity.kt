package com.lamour.akotlinprelude.TabActivity

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lamour.akotlinprelude.R
import android.support.v7.widget.Toolbar
import com.lamour.akotlinprelude.PlainFragment




abstract class TabActivity: AppCompatActivity() {

     lateinit public var tabLayout: TabLayout
     lateinit public var viewPager: ViewPager
     lateinit public var bar: Toolbar

    private var fragments = mutableListOf<PlainFragment>()

    // removing default provided actionBar
    // by changing the theme of this activity programmatically
    private fun removeDefaultActionBar() {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
    }

    // TODO: a better way to deal with the color for the window,tabBar and its items.
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setSubviews() {
        tabLayout = findViewById(R.id.TabLayoutId)
        viewPager = findViewById(R.id.viewPagerId)
        bar = findViewById(R.id.toolbarId)

        val androidLightBlue = Color.parseColor("#3F51B5")
        window.statusBarColor =  androidLightBlue
        bar.setBackgroundColor(androidLightBlue)
        tabLayout.setBackgroundColor(androidLightBlue)
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE)
        tabLayout.setSelectedTabIndicatorColor(Color.RED)
    }

    private fun setParentViewLayout() {
        setContentView(R.layout.activity_tab)
    }


    public fun setPrimaryConfiguration() {
         removeDefaultActionBar()
         setParentViewLayout()
         setSubviews()
    }

    // you should compose this activity
    // until you've provided a list of fragments
    // to host
    public fun compose() {
        setToolBar(this.bar)
        setupTabLayout(this.tabLayout)
        setupPager(this.viewPager,this.tabLayout)
    }


    public fun setFragments(fragments: List<PlainFragment>)  {
        fragments.forEach { this.fragments.add(it) }
    }

    private fun setToolBar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
    }

    private fun setupTabLayout(tLayout: TabLayout) {
        // create each tabItem
        this.fragments.forEach { tLayout.addTab(tLayout.newTab().setText(it.title)) }

        tLayout.tabGravity = TabLayout.GRAVITY_FILL

        tLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { viewPager.currentItem = tab.position }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setupPager(viewPager: ViewPager,tLayout: TabLayout) {
        if (!this.fragments.isEmpty()) {
            viewPager.adapter = TabViewPagerAdapter(supportFragmentManager, this.fragments)
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tLayout))
        }
    }
}