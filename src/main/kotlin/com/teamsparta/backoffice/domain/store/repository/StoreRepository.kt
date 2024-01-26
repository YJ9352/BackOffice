package com.teamsparta.backoffice.domain.store.repository

import com.teamsparta.backoffice.domain.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {
    fun findByUserId(userId: Long): List<Store>?
}