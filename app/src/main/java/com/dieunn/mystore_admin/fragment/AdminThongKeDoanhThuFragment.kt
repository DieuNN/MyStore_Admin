package com.dieunn.mystore_admin.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dieunn.mystore_admin.R
import com.dieunn.mystore_admin.adapter.SanPhamBanChayAdapter
import com.dieunn.mystore_admin.databinding.FragmentAdminThongKeDoanhThuBinding
import com.dieunn.mystore_admin.model.DonHang
import com.dieunn.mystore_admin.model.DonHangChiTiet
import com.dieunn.mystore_admin.model.TrangThai
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.FirebaseDatabase
import java.time.Month
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class AdminThongKeDoanhThuFragment : Fragment() {
    private lateinit var binding: FragmentAdminThongKeDoanhThuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminThongKeDoanhThuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateTotalIncome()
        setUpPieChart()
        setUpMonthlyChart()
        setUpBestSellerList()
    }

    private fun setUpPieChart() {
        FirebaseDatabase.getInstance().getReference("don_hang").get().addOnSuccessListener {
            val list = ArrayList<DonHang>()
            var completed = 0
            var unconfirmed = 0
            var canceled = 0
            var total = 0
            for (element in it.children) {
                val donHang = element.getValue(DonHang::class.java)
                donHang?.let { item ->
                    list.add(item)
                    total++
                    when (item.trangThai) {
                        TrangThai.HOAN_THANH.trangThai -> {
                            completed++
                        }
                        "Mới đặt" -> {
                            unconfirmed++
                        }
                        else -> {
                            canceled++
                        }
                    }
                }
            }

            val pieEntry = ArrayList<PieEntry>()
            pieEntry.add(PieEntry(completed / total.toFloat(), "Đã hoàn thành"))
            pieEntry.add(PieEntry(unconfirmed / total.toFloat(), "Chưa hoàn thành"))
            pieEntry.add(PieEntry(canceled / total.toFloat(), "Đã hủy"))

            val colors = ArrayList<Int>().apply {
                add(Color.GREEN)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    add(requireContext().getColor(R.color.blue))
                } else {
                    add(Color.BLUE)
                }
                add(Color.RED)
            }


            val pieDataSet = PieDataSet(pieEntry, "Tỉ lệ hủy, hoàn thành và chưa xác nhận đơn hàng")
            pieDataSet.colors = colors
            val pieData = PieData(pieDataSet)
            pieData.apply {
                this.setDrawValues(true)
                setValueFormatter(PercentFormatter(binding.thongKePieChart))
                setValueTextColor(Color.BLACK)
                setValueTextSize(12f)
            }

            binding.thongKePieChart.apply {
                data = pieData
                invalidate()
                isDrawHoleEnabled = true
                setUsePercentValues(true)
                setEntryLabelTextSize(12f)
                setEntryLabelColor(Color.BLACK)
                centerText = "Tỉ lệ hủy, hoàn thành và chưa xác nhận"
                setCenterTextSize(22f)
                description.isEnabled = false

                val pieLegend = this.legend
                pieLegend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
                pieLegend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                pieLegend.orientation = Legend.LegendOrientation.VERTICAL
                pieLegend.setDrawInside(false)
                pieLegend.isEnabled = true

            }

        }
    }


    private fun setUpMonthlyChart() {

        var result = 0f
        FirebaseDatabase.getInstance().getReference("don_hang").get().addOnSuccessListener {
            val donHangList = ArrayList<DonHang>()
            for (element in it.children) {
                val donHang = element.getValue(DonHang::class.java)
                if (donHang != null) {
                    donHangList.add(donHang)
                }

            }

            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val months = ArrayList<Int>()

            for (i in 1..12) {
                months.add(i)
            }

            val barEntry = ArrayList<BarEntry>()
            for (i in 1..12) {
                barEntry.add(BarEntry(i.toFloat(), getMonthlyEarning(i, donHangList)))
            }
            val barDataSet = BarDataSet(barEntry, "Thống kê doanh thu theo tháng").apply {
                colors = ColorTemplate.MATERIAL_COLORS.toList()
                valueTextColor = Color.BLACK
                valueTextSize = 16f
            }
            val barData = BarData(barDataSet)


            binding.thongKeBarChart.apply {
                setFitBars(true)
                data = barData
                description.text = "Thống kê doanh thu theo tháng"
                animateY(2000)
                invalidate()
                axisRight.setDrawLabels(false)

            }
        }


    }

    private fun getMonthlyEarning(month: Int, list: List<DonHang>): Float {
        var result = 0f

        for (element in list) {
            if (element.trangThai.equals("Đã hoàn thành") && Date(element.thoiGianGiaoHang).month + 1 == month) {
                result += element.tongTien
            }
        }


        return result
    }

    private fun setUpBestSellerList() {
        FirebaseDatabase.getInstance().getReference("don_hang").get().addOnSuccessListener {
            val donHangChiTietList = ArrayList<DonHangChiTiet>()
            for (element in it.children) {
                val donHang = element.getValue(DonHang::class.java)
                donHang?.let { item ->
                    for (element1 in item.donHangChiTietList) {
                        donHangChiTietList.add(element1)
                    }
                }
            }
            val soldItemIdList = ArrayList<String>()
            for (element in donHangChiTietList) {
                soldItemIdList.add(element.sanPham.id)
            }

            val soldItemIdSet = HashSet<String>()
            for (element in donHangChiTietList) {
                soldItemIdSet.add(element.sanPham.id)
            }

            val appearanceList = ArrayList<Int>()
            for (element in soldItemIdSet) {
                appearanceList.add(
                    calculateAppearances(
                        element,
                        soldItemIdList,
                        donHangChiTietList
                    )
                )
            }

            binding.thongKeDanhSachBanChay.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 4)
                adapter = SanPhamBanChayAdapter(soldItemIdSet.toList(), appearanceList)
            }
        }
    }

    private fun calculateAppearances(
        id: String,
        idList: List<String>,
        donHangChiTietList: List<DonHangChiTiet>
    ): Int {
        var appearance = 0
        for (element in idList) {
            if (element == id) appearance++
        }
        return appearance
    }

    private fun calculateTotalIncome() {
        var totalIncome = 0
        FirebaseDatabase.getInstance().getReference("don_hang").get().addOnSuccessListener {
            val donHangList = ArrayList<DonHang>()
            for (element in it.children) {
                val donHang = element.getValue(DonHang::class.java)
                donHang?.let { item ->
                    donHangList.add(item)
                }
            }
            for (element in donHangList) {
                if (element.trangThai.equals("Đã hoàn thành")) {
                    totalIncome += element.tongTien
                }
            }
            binding.thongKeTongDoanhThu.text = "Tổng thu nhập mọi thời điểm: $totalIncome"
            binding.thongKeTongDoanhThu.setTextColor(Color.GREEN)
        }
    }


}