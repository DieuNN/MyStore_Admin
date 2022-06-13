package com.dieunn.mystore_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dieunn.mystore_admin.databinding.FragmentAdminQuanLyKhachHangBinding


class AdminQuanLyKhachHangFragment : Fragment() {
  private lateinit var binding: FragmentAdminQuanLyKhachHangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminQuanLyKhachHangBinding.inflate(layoutInflater)
        return binding.root
    }


}