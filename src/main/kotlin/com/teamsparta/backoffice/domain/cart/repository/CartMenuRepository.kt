package com.teamsparta.backoffice.domain.cart.repository

import com.teamsparta.backoffice.domain.cart.model.CartMenu
import org.springframework.data.jpa.repository.JpaRepository

interface CartMenuRepository : JpaRepository<CartMenu, Long> {
    fun findByCartId(cartId: Long): List<CartMenu>

    fun deleteByCartId(cartId: Long)

}