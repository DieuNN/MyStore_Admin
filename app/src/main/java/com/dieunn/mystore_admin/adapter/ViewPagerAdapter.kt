package com.dieunn.mystore_admin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dieunn.mystore_admin.fragment.AdminCuaHangCacLoaiSanPham
import com.dieunn.mystore_admin.fragment.AdminCuaHangCacSanPham


class ViewPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) return AdminCuaHangCacSanPham()
        else return AdminCuaHangCacLoaiSanPham()
    }
}