package com.dieunn.mystore_admin.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.adapter.AdminSanPhamAdapter
import com.dieunn.mystore_admin.databinding.AdminBottomsheetThemSanPhamBinding
import com.dieunn.mystore_admin.databinding.FragmentAdminCuaHangCacSanPhamBinding
import com.dieunn.mystore_admin.model.LoaiSanPham
import com.dieunn.mystore_admin.model.SanPham
import com.dieunn.mystore_admin.utils.Converter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class AdminCuaHangCacSanPham : Fragment() {
    private lateinit var binding: FragmentAdminCuaHangCacSanPhamBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminCuaHangCacSanPhamBinding.inflate(layoutInflater)
        onEvent()
        setupProductList()
        return binding.root
    }

    private fun onEvent() {
        binding.adminCuaHangCacSanPhamThemSanPham.setOnClickListener {
            binding.adminCuaHangCacSanPhamThemSanPham.visibility = View.GONE
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetBinding =
                AdminBottomsheetThemSanPhamBinding.inflate(LayoutInflater.from(requireContext()))
            bottomSheetDialog.setContentView(bottomSheetBinding.root)
            bottomSheetDialog.show()

            bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.setOnClickListener {
                showProductTypeDialog(bottomSheetBinding)
            }

            bottomSheetDialog.setOnDismissListener {
                binding.adminCuaHangCacSanPhamThemSanPham.visibility = View.VISIBLE
            }

            bottomSheetBinding.adminBottomsheetThemSanPhamDongSheet.setOnClickListener {
                binding.adminCuaHangCacSanPhamThemSanPham.visibility = View.VISIBLE
                bottomSheetDialog.dismiss()
            }

            binding.adminCuaHangCacSanPhamThemSanPham.setOnClickListener {
                bottomSheetDialog.show()
            }

            bottomSheetBinding.adminBottomsheetThemSanPhamThemSanPham.setOnClickListener {
                addProduct(bottomSheetBinding, bottomSheetDialog)
            }


        }
    }

    private fun addProduct(
        bottomSheetBinding: AdminBottomsheetThemSanPhamBinding,
        bottomSheetDialog: BottomSheetDialog
    ) {
        if (bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.text.contains(" ")) {
            Toast.makeText(
                context,
                "Mã loại sản phẩm không hợp lệ!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.text.toString()
                .toFloat() < 0 ||
            bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.text.toString()
                .toFloat() > 1
        ) {
            Toast.makeText(context, "Khoảng khuyến mại nằm từ 0 -> 1", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            bottomSheetBinding.adminBottomsheetThemSanPhamEdtTenGiaBan.text.toString()
                .toInt()
            bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.text.toString()
                .toFloat()

            val sanPham = SanPham(
                UUID.randomUUID().toString(),
                bottomSheetBinding.adminBottomsheetThemSanPhamEdtTen.text.toString(),
                bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.text.toString(),
                bottomSheetBinding.adminBottomsheetThemSanPhamEdtMoTa.text.toString(),
                bottomSheetBinding.adminBottomsheetThemSanPhamEdtTenGiaBan.text.toString()
                    .toInt(),
                bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.text.toString()
                    .toFloat(),
                Converter.parseStringToColorList(bottomSheetBinding.adminBottomsheetThemSanPhamEdtMauSac.text.toString()),
                Converter.parseStringToSizeList(bottomSheetBinding.adminBottomsheetThemSanPhamEdtKichCo.text.toString()),
                Converter.parseStringToImageLinkList(bottomSheetBinding.adminBottomsheetThemSanPhamEdtLinkAnh.text.toString())
            )
            FirebaseDatabase.getInstance().getReference("san_pham").child(sanPham.id)
                .setValue(sanPham).addOnSuccessListener {
                    bottomSheetDialog.dismiss()
                    Toast.makeText(context, "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, "Kiểm tra lại nhập liệu!", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun showProductTypeDialog(bottomSheetBinding: AdminBottomsheetThemSanPhamBinding) {
        FirebaseDatabase.getInstance().getReference("loai_sp").get()
            .addOnSuccessListener {
                val result = ArrayList<LoaiSanPham>()
                for (element in it.children) {
                    result.add(element.getValue(LoaiSanPham::class.java)!!)
                }
                val resultAsStringList = ArrayList<String>().apply {
                    for (element in result) {
                        this.add(element.name)
                    }
                }
                AlertDialog.Builder(context).apply {
                    val dialogChonLoaiSP = LayoutInflater.from(context)
                        .inflate(
                            R.layout.admin_chon_loai_san_pham,
                            binding.root,
                            false
                        )
                    val list: ListView =
                        dialogChonLoaiSP.findViewById(R.id.list_view_loai_san_pham)
                    list.adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_list_item_1,
                        resultAsStringList
                    )
                    list.setOnItemClickListener { parent, view, position, id ->
                        bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.setText(
                            result[position].id
                        )
                        this.create().dismiss()
                    }
                    setView(dialogChonLoaiSP)
                }.create().show()
            }
    }

    private fun setupProductList() {
        val list = ArrayList<SanPham>()
        firebaseDatabase.getReference("san_pham").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children) {
                    val donHang = data.getValue(SanPham::class.java)
                    donHang?.let { list.add(it) }
                }
                binding.adminCuaHangCacSanPhamDanhSach.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = AdminSanPhamAdapter(list, requireContext())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Lỗi: $error", Toast.LENGTH_SHORT).show()
            }
        })
        binding.adminCuaHangCacSanPhamDanhSach.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = AdminSanPhamAdapter(list, requireContext())
        }
    }

}