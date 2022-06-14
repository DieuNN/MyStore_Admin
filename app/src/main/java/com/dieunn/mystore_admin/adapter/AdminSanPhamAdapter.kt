package com.dieunn.mystore_admin.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.databinding.AdminBottomsheetThemSanPhamBinding
import com.dieunn.mystore_admin.databinding.AdminSanPhamItemBinding
import com.dieunn.mystore_admin.model.LoaiSanPham
import com.dieunn.mystore_admin.model.SanPham
import com.dieunn.mystore_admin.utils.Converter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.lang.Exception

class AdminSanPhamAdapter(val list: List<SanPham>, val context: Context) :
    RecyclerView.Adapter<AdminSanPhamAdapter.ViewHolder>() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    class ViewHolder(val binding: AdminSanPhamItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = AdminSanPhamItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                try {
                    Picasso.get().load(this.anh[0].image).error(R.drawable.fire_fox)
                        .into(binding.adminSanPhamItemAnh)
                } catch (e: Exception) {

                }
                binding.adminSanphamItemGiaTruoc.text = this.gia_ban.toString()
                binding.adminSanphamItemTen.text = this.name
                binding.adminSanphamItemGiaSau.text =
                    (this.gia_ban - this.gia_ban * this.khuyen_mai).toString()
                binding.adminSanphamItemGiaTruoc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                binding.adminSanPhamItemXoa.setOnClickListener {
                    AlertDialog.Builder(context).apply {
                        setTitle("Xác nhận xóa sản phẩm này?")
                        setNegativeButton("Hủy") { dialog, _ ->
                            dialog.dismiss()
                        }
                        setPositiveButton("Xóa") { _, _ ->
                            firebaseDatabase.getReference("san_pham").child(id).removeValue()
                        }
                    }.create().show()
                }
                binding.adminSanPhamItem.setOnClickListener {
                    val bottomSheetDialog = BottomSheetDialog(context)
                    val bottomSheetBinding =
                        AdminBottomsheetThemSanPhamBinding.inflate(LayoutInflater.from(context))
                    bottomSheetBinding.adminBottomsheetThemSanPhamTieuDe.text = "Thay đổi thông tin"
                    bottomSheetDialog.setContentView(bottomSheetBinding.root)

                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtTen.setText(name)
                    FirebaseDatabase.getInstance().getReference("loai_sp").child(loai_sp).get()
                        .addOnSuccessListener {
                            val loaiSanPham = it.getValue(LoaiSanPham::class.java)
                            bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.setText(
                                loaiSanPham?.name
                            )
                        }
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.setText(
                        khuyen_mai.toString()
                    )
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtLinkAnh.setText(
                        Converter.parseImageLinkListToString(
                            anh
                        )
                    )
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtKichCo.setText(
                        Converter.parseSizeListToString(
                            size
                        )
                    )
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtMauSac.setText(
                        Converter.parseSColorListToString(
                            mauSac
                        )
                    )
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtMoTa.setText(moTa)
                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtTenGiaBan.setText(gia_ban.toString())

                    bottomSheetDialog.show()

                    bottomSheetBinding.adminBottomsheetThemSanPhamDongSheet.setOnClickListener {
                        bottomSheetDialog.dismiss()
                    }

                    bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.setOnClickListener { view ->
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
                                val dialogChonLoaiSP = LayoutInflater.from(context)
                                    .inflate(
                                        R.layout.admin_chon_loai_san_pham,
                                        holder.binding.root,
                                        false
                                    )

                               val alertDialog =  AlertDialog.Builder(context).apply {
                                    setView(dialogChonLoaiSP)
                                }.create()
                                alertDialog.show()
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
                                    alertDialog.dismiss()
                                }
                            }
                    }

                    bottomSheetBinding.adminBottomsheetThemSanPhamThemSanPham.setOnClickListener {
                        if (bottomSheetBinding.adminBottomsheetThemSanPhamEdtLoaiSp.text.contains(" ")) {
                            Toast.makeText(
                                context,
                                "Mã loại sản phẩm không hợp lệ!",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@setOnClickListener
                        }

                        try {
                            bottomSheetBinding.adminBottomsheetThemSanPhamEdtTenGiaBan.text.toString()
                                .toInt()
                            bottomSheetBinding.adminBottomsheetThemSanPhamEdtPhanTramKhuyenMai.text.toString()
                                .toFloat()

                            val sanPham: SanPham = SanPham(
                                id,
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

                            FirebaseDatabase.getInstance().getReference("san_pham").child(id)
                                .setValue(sanPham).addOnSuccessListener {
                                Toast.makeText(context, "Thay đổi thành công!", Toast.LENGTH_SHORT)
                                    .show()
                                bottomSheetDialog.dismiss()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Kiểm tra lại nhập liệu!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}