package com.augusto.moneymanage.service

import com.augusto.moneymanage.dto.TransactionInDTO
import com.augusto.moneymanage.dto.TransactionOutDTO
import com.augusto.moneymanage.model.Transaction
import com.augusto.moneymanage.model.TransactionType
import com.augusto.moneymanage.model.User
import com.augusto.moneymanage.repository.TransactionRepository
import com.augusto.moneymanage.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Service
class TransactionService (private val transactionRepository: TransactionRepository, private val userRepository: UserRepository) {

    fun save(transactionInDto: TransactionInDTO, userId: Long): Transaction {
        val user = User(userId)
        val (name, description, amount, type, date) = transactionInDto
        val transaction = Transaction(0L, name, description, amount, type, date, user)

        return transactionRepository.save(transaction)
    }

    fun update(id: Long, userId: Long, transactionInDto: TransactionInDTO): Transaction {
        val (name, description, amount, type, date) = transactionInDto
        val user = User(userId)
        val transaction = Transaction(id, name, description, amount, type, date, user)

        return transactionRepository.save(transaction)
    }

    fun delete(id: Long) {
        transactionRepository.deleteById(id)
    }

    fun existByIdAndUserId(id: Long, userId: Long): Boolean {
        return transactionRepository.existsByIdAndUserId(id, userId)
    }

    fun getByDate(startDate: Date, userId: Long): TransactionOutDTO {
        val calendar = Calendar.getInstance()

        calendar.time = startDate
        calendar.set(Calendar.DATE, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val endOfMonth: Calendar = (calendar.clone() as Calendar)
        endOfMonth.add(Calendar.MONTH, 1)

        val transactions: List<Transaction> = transactionRepository
            .findByUserIdAndDateBetween(
                userId,
                calendarToLocalDateTime(calendar),
                calendarToLocalDateTime(endOfMonth)
            )

        val amountExpense = sumByType(transactions, TransactionType.EXPENSE)
        val amountIncome = sumByType(transactions, TransactionType.INCOME)

        return TransactionOutDTO(date = calendar.time, amountExpense = amountExpense,  amountIncome = amountIncome, transactions)
    }

    private fun sumByType(transactions: List<Transaction>, transactionType: TransactionType): Double {
        return transactions
            .filter { it.type == transactionType }.sumOf { it.amount }
    }

    private fun calendarToLocalDateTime(calendar: Calendar): LocalDateTime {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault())
    }
}