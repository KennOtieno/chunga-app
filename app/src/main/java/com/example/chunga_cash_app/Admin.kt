package com.example.chunga_cash_app

data class Admin (val firstName: String, val lastName: String, val username: String, private val password: String, val email: String,
                    val institution: String) {

}
