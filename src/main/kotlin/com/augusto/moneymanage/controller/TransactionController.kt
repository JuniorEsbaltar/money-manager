package com.augusto.moneymanage.controller

import com.augusto.moneymanage.dto.TransactionOutDTO
import com.augusto.moneymanage.model.Transaction
import com.augusto.moneymanage.service.TransactionService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
    fun getAll(@PathVariable date: String): ResponseEntity<List<TransactionOutDTO>> {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(date)
        val list = transactionService.findByDate(dateFormat)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(list)
    }

    @PostMapping
    fun save(@RequestBody transaction: Transaction): ResponseEntity<Transaction> {
        val transaction = transactionService.save(transaction)
        println(transaction.toString())
        return ResponseEntity.ok().body(transaction)
    }

}