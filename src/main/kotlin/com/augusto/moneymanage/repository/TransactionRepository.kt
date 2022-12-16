package com.augusto.moneymanage.repository

import com.augusto.moneymanage.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByDateBetweenAndIsRecurringIsFalse(start: LocalDateTime, end: LocalDateTime): List<Transaction>

    fun findByIsRecurringIsTrue(): List<Transaction>
}