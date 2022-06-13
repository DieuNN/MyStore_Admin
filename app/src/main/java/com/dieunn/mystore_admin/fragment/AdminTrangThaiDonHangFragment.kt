package com.dieunn.mystore_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dieunn.mystore_admin.adapter.TrangThaiDonHangAdapter
import com.dieunn.mystore_admin.databinding.FragmentAdminTrangThaiDonHangBinding
import com.dieunn.mystore_admin.model.DonHang
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AdminTrangThaiDonHang : Fragment() {
    private lateinit var binding: FragmentAdminTrangThaiDonHangBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminTrangThaiDonHangBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        val list = ArrayList<DonHang>()
        firebaseDatabase.getReference("don_hang").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children) {
                    val donHang = data.getValue(DonHang::class.java)
                    donHang?.let { list.add(it) }
                }
                binding.adminFragmentTrangThaiDonHangList.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = TrangThaiDonHangAdapter(list,requireContext())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Lỗi: $error", Toast.LENGTH_SHORT).show()
            }
        })
        binding.adminFragmentTrangThaiDonHangList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TrangThaiDonHangAdapter(list, requireContext())
        }
    }


}