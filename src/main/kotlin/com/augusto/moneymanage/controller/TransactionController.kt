package com.augusto.moneymanage.controller

import com.augusto.moneymanage.dto.TransactionInDTO
import com.augusto.moneymanage.dto.TransactionOutDTO
import com.augusto.moneymanage.model.Transaction
import com.augusto.moneymanage.model.User
import com.augusto.moneymanage.service.TransactionService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat

@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping("/{date}")
    fun getOne(@PathVariable date: String): ResponseEntity<TransactionOutDTO> {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(date)
        val transactions: TransactionOutDTO = transactionService.getByDate(dateFormat)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(transactions)
    }

    @PostMapping
    fun save(@RequestBody transactionInDto: TransactionInDTO, request: HttpServletRequest): ResponseEntity<Void> {
        val id = request.getAttribute("id") as Long
        val user = User(id)
        val (name, amount, date, type, description) = transactionInDto
        val transaction = Transaction(0L, name, amount, date, type, description, user)

        transactionService.save(transaction)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        transactionService.delete(id)
        return ResponseEntity.noContent().build()
    }
}