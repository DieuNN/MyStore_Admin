package com.dieunn.mystore_admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.databinding.KhachHangItemBinding
import com.dieunn.mystore_admin.model.User
import com.google.firebase.database.FirebaseDatabase

class KhachHangAdapter(
    val list: ArrayList<User>
) : RecyclerView.Adapter<KhachHangAdapter.ViewHolder>() {
    class ViewHolder(val binding: KhachHangItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(KhachHangItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.khachHangItemTen.text = this.name
                binding.khachHangItemEmail.text = this.email
                binding.khachHangItemSoDienThoai.text = this.phone_number
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}