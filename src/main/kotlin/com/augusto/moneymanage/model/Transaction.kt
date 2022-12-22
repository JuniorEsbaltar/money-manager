package com.augusto.moneymanage.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val name: String = "",

    @Column
    val description: String = "",

    @Column(nullable = false)
    val amount: Double = 0.0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: TransactionType = TransactionType.EXPENSE,

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val date: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    var user: User? = User(),
)
enum class TransactionType {
    EXPENSE,
    INCOME
}

