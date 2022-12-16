package com.augusto.moneymanage.service

import com.augusto.moneymanage.dto.TransactionOutDTO
import com.augusto.moneymanage.model.Transaction
import com.augusto.moneymanage.model.TransactionType
import com.augusto.moneymanage.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Service
class TransactionService (private val transactionRepository: TransactionRepository) {

    fun save(transaction: Transaction): Transaction {
        return this.transactionRepository.save(transaction)
    }

    fun findByDate(startDate: Date): List<TransactionOutDTO>  {
        val calendar = Calendar.getInstance()

        val months = mutableListOf<Calendar>()
        val transactionsDTO = mutableListOf<TransactionOutDTO>()

        calendar.time = startDate
        calendar.add(Calendar.MONTH, -2)
        calendar.set(Calendar.DATE, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        for (i in 0 until 9) {
            val calendarClone = calendar.clone() as Calendar
            calendarClone.add(Calendar.MONTH, i)
            months.add(calendarClone)
        }

        val transactionsRecurring = this.transactionRepository.findByIsRecurringIsTrue()

        months.forEach {date ->
            val endOfMonth: Calendar = (date.clone() as Calendar)
            endOfMonth.add(Calendar.MONTH, 1)

            val transactions: List<Transaction> = this.transactionRepository
                .findByDateBetweenAndIsRecurringIsFalse(
                    calendarToLocalDateTime(date),
                    calendarToLocalDateTime(endOfMonth)
                )

            val amountExpense = sumByType(transactions + transactionsRecurring, TransactionType.EXPENSE)
            val amountIncome = sumByType(transactions + transactionsRecurring, TransactionType.INCOME)

            val transactionDTO = TransactionOutDTO(date = date, amountExpense = amountExpense,  amountIncome = amountIncome, transactions + transactionsRecurring)
            transactionsDTO.add(transactionDTO)
        }

        return transactionsDTO.toList()
    }

    private fun sumByType(transactions: List<Transaction>, transactionType: TransactionType): Double {
        return transactions
            .filter { it.type == transactionType }.sumOf { it.amount }
    }

    private fun calendarToLocalDateTime(calendar: Calendar): LocalDateTime {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault())
    }
}