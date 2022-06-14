package com.dieunn.mystore_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dieunn.mystore_admin.adapter.KhachHangAdapter
import com.dieunn.mystore_admin.databinding.FragmentAdminQuanLyKhachHangBinding
import com.dieunn.mystore_admin.model.User
import com.google.firebase.database.FirebaseDatabase


class AdminQuanLyKhachHangFragment : Fragment() {
    private lateinit var binding: FragmentAdminQuanLyKhachHangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminQuanLyKhachHangBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUserList()
    }

    private fun setUpUserList() {
        FirebaseDatabase.getInstance().getReference("users").get().addOnSuccessListener {
            val users = ArrayList<User>()
            for (element in it.children) {
                users.add(element.getValue(User::class.java)!!)
            }

            binding.khachHangList.apply {
                layoutManager = GridLayoutManager(requireContext(), 1)
                adapter = KhachHangAdapter(users)
            }
        }
    }


}