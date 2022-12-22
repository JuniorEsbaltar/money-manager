package com.augusto.moneymanage.dto

import com.augusto.moneymanage.model.Transaction
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class TransactionOutDTO(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val date: Date,
    val amountExpense: Double,
    val amountIncome: Double,
    val transactions: List<Transaction>,
) {

}