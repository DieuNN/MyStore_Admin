package com.dieunn.mystore_admin.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.databinding.AdminDonHangChiTietDialogBinding
import com.dieunn.mystore_admin.databinding.AdminTrangThaiDonHangItemBinding
import com.dieunn.mystore_admin.model.DonHang
import com.dieunn.mystore_admin.utils.Converter
import com.google.firebase.database.FirebaseDatabase


class TrangThaiDonHangAdapter(var data: ArrayList<DonHang>, val context: Context) :
    RecyclerView.Adapter<TrangThaiDonHangAdapter.ViewHolder>() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    class ViewHolder(val binding: AdminTrangThaiDonHangItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdminTrangThaiDonHangItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(data[position]) {
                binding.adminNavTrangThaiDonHangItemDonHang.text = "Mã: ${this.id}"
                binding.adminNavTrangThaiDonHangItemKhachHang.text = "Tên khách hàng: ${this.name}"
                binding.adminNavTrangThaiDonHangItemSoDienThoai.text =
                    "Số điện thoại khách: ${this.phone_number}"
                binding.adminNavTrangThaiDonHangItemGhiChu.text = "Ghi chú: " + this.ghiChu
                binding.adminNavTrangThaiDonHangItemTongGiaTri.text =
                    "Tổng tiền" + this.tongTien.toString()
                binding.adminNavTrangThaiDonHangItemTrangThai.text = this.trangThai

                if (this.trangThai.toString().contains("Hủy")) {
                    binding.adminNavTrangThaiDonHangItemTrangThai.setTextColor(android.graphics.Color.RED)
                } else if (this.trangThai.toString().contains("Đã")) {
                    binding.adminNavTrangThaiDonHangItemTrangThai.setTextColor(android.graphics.Color.GREEN)
                } else {
                    binding.adminNavTrangThaiDonHangItemTrangThai.setTextColor(android.graphics.Color.BLUE)
                    binding.adminNavTrangThaiDonHangItemXacNhan.visibility = View.VISIBLE
                }

                binding.adminNavTrangThaiDonHangItemXemChiTiet.setOnClickListener {
                    showDetail(this)
                }

                binding.adminNavTrangThaiDonHangItemXoa.setOnClickListener {
                    showConfirmDeleteOrder(position, this)
                }

                this.trangThai.contains("Chưa", ignoreCase = true).also {
                    binding.adminNavTrangThaiDonHangItemXacNhan.setOnClickListener {
                        showConfirmCompletedOrder(position, this)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    private fun showDetail(donHang: DonHang) {
        val dialog = Dialog(context)
        val binding =
            AdminDonHangChiTietDialogBinding.inflate(LayoutInflater.from(context))

        binding.apply {
            this.adminDialogDonHangChiTietMaDonHang.text = donHang.id
            this.adminDialogDonHangChiTietTenKhachHang.text = donHang.name
            this.adminDialogDonHangChiTietSoDienThoai.text = donHang.phone_number
            this.adminDialogDonHangChiTietThoiGianDat.text = donHang.thoiGianDatHang
            this.adminDialogDonHangChiTietThoiGianGiao.text =
                Converter.parseLongTimeToString(donHang.thoiGianGiaoHang)
            this.adminDialogDonHangChiTietTongSoTien.text = donHang.tongTien.toString()
            this.adminDialogDonHangChiTietTrangThai.text = donHang.trangThai

            if (donHang.trangThai.toString().contains("Hủy")) {
                binding.adminDialogDonHangChiTietTrangThai.setTextColor(android.graphics.Color.RED)
            } else if (donHang.trangThai.toString().contains("Đã")) {
                binding.adminDialogDonHangChiTietTrangThai.setTextColor(android.graphics.Color.GREEN)
            } else {
                binding.adminDialogDonHangChiTietTrangThai.setTextColor(android.graphics.Color.BLUE)
            }

            this.adminDialogDonHangChiTietDanhSach.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DialogDonHangChiTietAdapter(donHang.donHangChiTietList)
            }

        }
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.setContentView(binding.root)
        dialog.show()

    }

    private fun showConfirmDeleteOrder(position: Int, donHang: DonHang) {
        AlertDialog.Builder(context).apply {
            setTitle("Xác nhận xóa đơn hàng")
            setMessage("Xóa đơn hàng sẽ không thể hoàn tác. Xác nhận xóa?")
            setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton("Xóa") { _, _ ->
                firebaseDatabase.getReference("don_hang").child(donHang.id).removeValue().apply {
                    this@TrangThaiDonHangAdapter.notifyItemRemoved(position)
                    this@TrangThaiDonHangAdapter.notifyItemRangeChanged(0, itemCount - 1)
                }
            }
        }.create().show()
    }

    private fun showConfirmCompletedOrder(position: Int, donHang: DonHang) {
        AlertDialog.Builder(context).apply {
            setTitle("Xác nhận hoàn thành đơn hàng")
            setMessage("Hãy chắc chắn rằng đơn hàng đã được giao đến người nhận thành công. Sau khi xác nhận đơn hàng, đơn hàng sẽ chuyển sang trạng thái đã thanh toán và không thể hoàn tác.")
            setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton("Xác nhận") { _, _ ->
                firebaseDatabase.getReference("don_hang").child(donHang.id).child("trangThai").setValue("Đã hoàn thành").addOnSuccessListener {
                    Toast.makeText(context, "Thành công!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(context, "Thất bại!", Toast.LENGTH_SHORT).show()
                }
            }
        }.create().show()
    }
}