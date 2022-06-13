package com.dieunn.mystore_admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.databinding.ItemLoaispBinding
import com.dieunn.mystore_admin.model.SanPham
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class SanPhamBanChayAdapter(
    val idList: List<String>,
    val amountList: ArrayList<Int>
) : RecyclerView.Adapter<SanPhamBanChayAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemLoaispBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLoaispBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(idList[position]) {
                FirebaseDatabase.getInstance().getReference("san_pham").child(this).get()
                    .addOnSuccessListener {
                        val sanPham = it.getValue(SanPham::class.java)
                        binding.tvLoaisp.text = sanPham?.name
                        binding.tvSoLuong.apply {
                            visibility = View.VISIBLE
                            text = amountList[position].toString()
                        }
                        Picasso.get().load(sanPham!!.anh[0].image)
                            .placeholder(R.drawable.fire_fox)
                            .error(R.drawable.fire_fox)
                            .into(binding.imageLoaisp)
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return idList.size
    }
}