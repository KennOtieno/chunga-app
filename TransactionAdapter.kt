package com.example.chunga_cash_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recylerview.widget.RecylerView
class TransactionAdapter : RecylerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactions: List<Transaction> = emptyList()

    fun setTransactions(transactions: List<Transaction>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }
}