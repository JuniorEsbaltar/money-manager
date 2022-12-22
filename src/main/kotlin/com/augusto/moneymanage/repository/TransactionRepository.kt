package com.augusto.moneymanage.repository

import com.augusto.moneymanage.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByDateBetween(start: LocalDateTime, end: LocalDateTime): List<Transaction>
}