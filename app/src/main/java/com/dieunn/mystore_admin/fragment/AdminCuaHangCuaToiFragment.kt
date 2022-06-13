package com.dieunn.mystore_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dieunn.mystore_admin.adapter.ViewPagerAdapter
import com.dieunn.mystore_admin.databinding.FragmentAdminCuaHangCuaToiBinding
import com.google.android.material.tabs.TabLayout


class AdminCuaHangCuaToiFragment : Fragment() {
    private lateinit var binding: FragmentAdminCuaHangCuaToiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminCuaHangCuaToiBinding.inflate(layoutInflater)
        initViewPager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViewPager() {
        val fragmentManager = activity?.supportFragmentManager
        val adapter = ViewPagerAdapter(fragmentManager!!,lifecycle)
        binding.adminCuaHangCuaToiViewPager.adapter = adapter

        binding.adminCuaHangCuaToiTabLayout.addTab(binding.adminCuaHangCuaToiTabLayout.newTab().setText("Sản phẩm"))
        binding.adminCuaHangCuaToiTabLayout.addTab(binding.adminCuaHangCuaToiTabLayout.newTab().setText("Loại sản phẩm"))

        binding.adminCuaHangCuaToiTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.adminCuaHangCuaToiViewPager.currentItem = it }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.adminCuaHangCuaToiViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.adminCuaHangCuaToiTabLayout.selectTab(binding.adminCuaHangCuaToiTabLayout.getTabAt(position))
            }
        })
    }




}