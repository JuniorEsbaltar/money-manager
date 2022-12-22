package com.augusto.moneymanage.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(nullable = false)
    val token: String = "",

    val name: String = "",

    @OneToMany(mappedBy = "user")
    val transactions: List<Transaction?> = mutableListOf<Transaction>().toList()
)