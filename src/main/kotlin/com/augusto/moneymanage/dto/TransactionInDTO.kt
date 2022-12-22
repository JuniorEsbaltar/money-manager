package com.augusto.moneymanage.dto

import com.augusto.moneymanage.model.TransactionType
import java.time.LocalDateTime

data class TransactionInDTO(
    val name: String,
    val description: String,
    val amount: Double,
    val type: TransactionType,
    val date: LocalDateTime,
)