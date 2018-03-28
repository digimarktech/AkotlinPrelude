package com.lamour.akotlinprelude.TabActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabViewPagerAdapter(manager: FragmentManager, val fragments: List<Fragment>) : FragmentStatePagerAdapter(manager) {

    override fun getCount(): Int = this.fragments.size

    override  fun getItem(position: Int): Fragment = this.fragments[position]

}