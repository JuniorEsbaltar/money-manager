package com.augusto.moneymanage.dto

import com.augusto.moneymanage.model.Transaction
import java.util.Calendar

class TransactionOutDTO(
    val date: Calendar,
    val amountExpense: Double,
    val amountIncome: Double,
    val transactions: List<Transaction>,
) {

}