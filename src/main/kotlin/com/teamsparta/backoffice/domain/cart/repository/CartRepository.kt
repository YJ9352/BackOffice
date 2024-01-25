package com.teamsparta.backoffice.domain.cart.repository

import com.teamsparta.backoffice.domain.cart.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart,Long> {

    fun findByUserId(userId:Long):Cart?

    fun deleteByUserId(userId: Long)

}