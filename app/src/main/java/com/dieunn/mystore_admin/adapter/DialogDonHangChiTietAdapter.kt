package com.dieunn.mystore_admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.databinding.AdminDialogDonHangChiTietItemChiTietBinding
import com.dieunn.mystore_admin.model.DonHangChiTiet
import com.squareup.picasso.Picasso

class DialogDonHangChiTietAdapter(val list: List<DonHangChiTiet>) :
    RecyclerView.Adapter<DialogDonHangChiTietAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdminDialogDonHangChiTietItemChiTietBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdminDialogDonHangChiTietItemChiTietBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                Picasso.get().load(this.sanPham.anh[0].image)
                    .into(binding.adminDialogDonHangChiTietItemChiTietAnh)
                binding.adminDialogDonHangChiTietItemChiTietTenSanPham.text = this.sanPham.name
                binding.adminDialogDonHangChiTietItemChiTietGiaTien.text =
                    this.sanPham.gia_ban.toString()
                binding.adminDialogDonHangChiTietItemChiTietSoLuong.text = this.soLuong.toString()
                binding.adminDialogDonHangChiTietItemChiTietMauSac.text =
                    this.sanPham.mauSac[0].color.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}