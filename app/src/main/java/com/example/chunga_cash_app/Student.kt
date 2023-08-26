package com.example.chunga_cash_app

import java.math.BigDecimal

data class Student (val firstName: String, val lastName: String, val admNum: Int, val photo: String,
                    private val pinCode: Int, val accBalance: String) {


    fun getName(): String {
        return "$firstName $lastName"
    }

}

