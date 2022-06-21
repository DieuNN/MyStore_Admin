package com.dieunn.mystore_admin.utils

import com.dieunn.mystore_admin.model.Anh
import com.dieunn.mystore_admin.model.Color
import com.dieunn.mystore_admin.model.Size
import java.text.SimpleDateFormat
import java.util.*

class Converter {
    companion object {
        fun parseLongTimeToString(time: Long): String {
            return SimpleDateFormat("dd/MM/yyyy").format(Date(time))
        }

        fun parseImageLinkListToString(list: List<Anh>): String {
            var result = ""
            for (element in list) {
                result += element.image.trim() + ", "
            }
            // xoa dau phay o cuoi
            return result.substring(0, result.length - 2)
        }

        fun parseSizeListToString(list: List<Size>): String {
            var result = ""
            for (element in list) {
                result += element.size.trim() + ", "
            }
            // xoa dau phay o cuoi
            return result.substring(0, result.length - 2)
        }

        fun parseSColorListToString(list: List<Color>): String {
            var result = ""
            for (element in list) {
                result += element.color.trim() + ", "
            }
            // xoa dau phay o cuoi
            return result.substring(0, result.length - 2)
        }

        fun parseStringToImageLinkList(input: String): List<Anh> {
            val arraySpliced = input.split(",")
            val result = ArrayList<Anh>()
            for (element in arraySpliced) {
                result.add(
                    Anh(
                        element.trim(),
                        false
                    )
                )
            }
            return result
        }

        fun parseStringToSizeList(input: String): List<Size> {
            val arraySpliced = input.split(",")
            val result = ArrayList<Size>()
            for (element in arraySpliced) {
                result.add(
                    Size(
                        element.trim(),
                        false
                    )
                )
            }
            return result
        }

        fun parseStringToColorList(input: String): List<Color> {
            val arraySpliced = input.split(",")
            val result = ArrayList<Color>()
            for (element in arraySpliced) {
                result.add(
                    Color(
                        element.trim(),
                        false
                    )
                )
            }
            return result
        }


    }
}