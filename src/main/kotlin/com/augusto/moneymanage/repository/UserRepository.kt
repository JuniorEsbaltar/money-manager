package com.augusto.moneymanage.repository;

import com.augusto.moneymanage.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByToken(token: String): Optional<User>
}