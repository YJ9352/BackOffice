package com.teamsparta.backoffice.domain.store.dto.request

import com.teamsparta.backoffice.domain.store.model.StoreStatus

data class StoreStatusRequest(
    val status: String,
)