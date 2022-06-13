package com.dieunn.mystore_admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.databinding.AdminBottomsheetThemLoaiSpBinding
import com.dieunn.mystore_admin.databinding.ItemLoaispBinding
import com.dieunn.mystore_admin.model.LoaiSanPham
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class AdminThemLoaiSpAdapter(val list: ArrayList<LoaiSanPham>) :
    RecyclerView.Adapter<AdminThemLoaiSpAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemLoaispBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLoaispBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.apply {
                    Picasso.get().load(image).error(R.drawable.fire_fox)
                        .placeholder(R.drawable.fire_fox).into(this.imageLoaisp)
                    this.tvLoaisp.text = name
                    this.linearLayout.setOnClickListener {
                        val bottomSheetDialog = BottomSheetDialog(binding.root.context)
                        val bottomSheetBinding =
                            AdminBottomsheetThemLoaiSpBinding.inflate(LayoutInflater.from(binding.root.context))
                        bottomSheetDialog.setContentView(bottomSheetBinding.root)
                        bottomSheetDialog.apply {
                            create()
                            show()
                        }


                        bottomSheetBinding.apply {
                            adminBottomsheetThemLoaiSanPhamTieuDe.text = "Chỉnh sửa loại sản phẩm"
                            adminBottomsheetThemLoaiSanPhamEdtTen.setText(name)
                            adminBottomsheetThemLoaiSanPhamEdtLink.setText(image)
                            adminBottomsheetThemLoaiSanPhamDongSheet.setOnClickListener {
                                bottomSheetDialog.dismiss()
                            }
                            adminBottomsheetThemLoaiSanPhamBtnThem.setOnClickListener {
                                editProductType(bottomSheetDialog, bottomSheetBinding, this@with)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun editProductType(
        bottomSheetDialog: BottomSheetDialog,
        bottomSheetBinding: AdminBottomsheetThemLoaiSpBinding,
        loaiSanPham: LoaiSanPham
    ) {
        if (bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtTen.text.toString().isNotEmpty() &&
            bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtLink.text.toString().isNotEmpty()
        ) {
            val loaiSanPham  = LoaiSanPham(
                loaiSanPham.id,
                bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtTen.text.toString(),
                bottomSheetBinding.adminBottomsheetThemLoaiSanPhamEdtLink.text.toString()
            )
            FirebaseDatabase.getInstance().getReference("loai_sp").child(loaiSanPham.id).setValue(loaiSanPham).addOnSuccessListener {
                Toast.makeText(bottomSheetBinding.root.context, "Thay đổi thành công!", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }
        }
    }
}