package com.augusto.moneymanage.repository

import com.augusto.moneymanage.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.Optional

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByUserIdAndDateBetween(userId: Long, start: LocalDateTime, end: LocalDateTime): List<Transaction>
    fun existsByIdAndUserId(id: Long, userId: Long): Boolean
}