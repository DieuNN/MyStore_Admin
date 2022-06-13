package com.dieunn.mystore_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dieunn.mystore_admin.adapter.AdminThemLoaiSpAdapter
import com.dieunn.mystore_admin.databinding.AdminBottomsheetThemLoaiSpBinding
import com.dieunn.mystore_admin.databinding.FragmentAdminCuaHangCacLoaiSanPhamBinding
import com.dieunn.mystore_admin.model.LoaiSanPham
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class AdminCuaHangCacLoaiSanPham : Fragment() {
    private lateinit var binding: FragmentAdminCuaHangCacLoaiSanPhamBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminCuaHangCacLoaiSanPhamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        onEvent()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerView() {
        binding.cacLoaiSanPhamList.setHasFixedSize(true)
        binding.cacLoaiSanPhamList.layoutManager = GridLayoutManager(context, 4)
        var list = ArrayList<LoaiSanPham>()
        val adapter = AdminThemLoaiSpAdapter(list)
        binding.cacLoaiSanPhamList.adapter = adapter
        FirebaseDatabase.getInstance().getReference("loai_sp").addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (element in snapshot.children) {
                    list.add(element.getValue(LoaiSanPham::class.java)!!)
                }
                binding.cacLoaiSanPhamList.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun onEvent() {
        binding.fabThemLoaiSanPham.setOnClickListener {
            showBottomDialog()
        }
    }

    private fun showBottomDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = AdminBottomsheetThemLoaiSpBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.apply {
            create()
            show()
        }


        bottomSheetBinding.apply {
            adminBottomsheetThemLoaiSanPhamDongSheet.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            adminBottomsheetThemLoaiSanPhamBtnThem.setOnClickListener {
                addProductType(bottomSheetDialog, bottomSheetBinding)
            }
        }
    }

    private fun addProductType(
        bottomSheetDialog: BottomSheetDialog,
        bottomSheetBinding: AdminBottomsheetThemLoaiSpBinding
    ) {
        if (bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtTen.text.toString().isNotEmpty() &&
            bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtLink.text.toString().isNotEmpty()
        ) {
            val loaiSanPham = LoaiSanPham(
                UUID.randomUUID().toString(),
                bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtTen.text.toString(),
                bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtLink.text.toString()
            )
            FirebaseDatabase.getInstance().getReference("loai_sp").child(loaiSanPham.id)
                .setValue(loaiSanPham).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
            }
        } else {
            Toast.makeText(requireContext(), "Kiểm tra lại nhập liệu!", Toast.LENGTH_SHORT).show()
        }
    }

}