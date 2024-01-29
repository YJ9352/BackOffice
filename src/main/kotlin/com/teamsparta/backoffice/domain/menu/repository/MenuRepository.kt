package com.teamsparta.backoffice.domain.menu.repository

import com.teamsparta.backoffice.domain.menu.model.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByStoreId(storeId: Long): List<Menu>?
}