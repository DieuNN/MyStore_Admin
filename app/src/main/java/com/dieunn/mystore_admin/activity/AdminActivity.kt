package com.dieunn.mystore_admin.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.databinding.ActivityAdminBinding
import com.dieunn.mystore_admin.fragment.AdminCuaHangCuaToiFragment
import com.dieunn.mystore_admin.fragment.AdminQuanLyKhachHangFragment
import com.dieunn.mystore_admin.fragment.AdminThongKeDoanhThuFragment
import com.dieunn.mystore_admin.fragment.AdminTrangThaiDonHang
import com.google.android.material.navigation.NavigationView

class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setUpDrawerView()
        setContentView(binding.root)
    }

    private fun setUpDrawerView() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.admin_status_bar_color)
        setSupportActionBar(binding.adminToolbar)
        binding.adminToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        val toggle = ActionBarDrawerToggle(
            this,
            binding.adminDrawerLayout,
            binding.adminToolbar,
            R.string.action_bar_toggle_open,
            R.string.action_bar_toggle_close
        )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        binding.adminDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager.beginTransaction()
            .replace(binding.adminFragmentContainer.id, AdminTrangThaiDonHang()).commit()
        binding.adminNavigationView.setCheckedItem(R.id.admin_nav_trang_thai_don_hang)
        binding.adminToolbar.title = "Trạng thái đơn hàng"

        binding.adminNavigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.adminDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.adminDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.admin_nav_trang_thai_don_hang -> {
                binding.adminToolbar.title = "Trạng thái đơn hàng"
                fragment = AdminTrangThaiDonHang()
            }
            R.id.admin_nav_cua_hang_cua_toi -> {
                binding.adminToolbar.title = "Cửa hàng của tôi"
                fragment = AdminCuaHangCuaToiFragment()
            }
            R.id.admin_nav_thong_ke_doanh_thu -> {
                binding.adminToolbar.title = "Thống kê doanh thu"
                fragment = AdminThongKeDoanhThuFragment()
            }
            R.id.admin_nav_quan_ly_khach_hang -> {
                binding.adminToolbar.title = "Quản lý khách hàng"
                fragment = AdminQuanLyKhachHangFragment()
            }
            else -> performExit()
        }
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(binding.adminFragmentContainer.id, fragment).commit()
        }
        binding.adminDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun performExit() {
      AlertDialog.Builder(this).apply {
            setMessage("Xác nhận thoát")
            setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }
          setPositiveButton("Thoát"
            ) { _, _ ->
                onBackPressed()
            }
      }.create().show()
    }
}