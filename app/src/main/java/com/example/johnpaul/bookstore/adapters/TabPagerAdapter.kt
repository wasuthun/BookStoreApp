package com.example.johnpaul.bookstore.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class TabPagerAdapter(
        fm: FragmentManager,
        private var tabCount: Int,
        private vararg val tabs: Fragment
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        if (position < tabs.size) {
            return tabs.get(position)
        } else {
            return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}