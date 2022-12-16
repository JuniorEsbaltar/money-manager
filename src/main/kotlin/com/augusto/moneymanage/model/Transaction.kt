package com.augusto.moneymanage.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val amount: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: TransactionType,

    @Column(nullable = false)
    val isRecurring: Boolean,

    @Column(nullable = false)
    val date: LocalDateTime
){
    constructor(): this(0L, "", 0.0, TransactionType.EXPENSE, false, LocalDateTime.now())
}

enum class TransactionType {
    EXPENSE,
    INCOME
}

