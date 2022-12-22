package com.augusto.moneymanage.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var name: String = "",

    @Column
    var description: String = "",

    @Column(nullable = false)
    var amount: Double = 0.0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: TransactionType = TransactionType.EXPENSE,

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var date: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JsonIgnore
    var user: User? = User(),
)
enum class TransactionType {
    EXPENSE,
    INCOME
}

