package com.teamsparta.backoffice.domain.store.service

import com.teamsparta.backoffice.domain.store.dto.request.StoreRequest
import com.teamsparta.backoffice.domain.store.dto.request.StoreStatusRequest
import com.teamsparta.backoffice.domain.store.dto.response.StoreListResponse
import com.teamsparta.backoffice.domain.store.dto.response.StoreResponse
import com.teamsparta.backoffice.domain.store.dto.response.UserStoreListResponse

interface StoreService {

    // 가게 목록 조회(사용자)
    fun getStoreList(): List<UserStoreListResponse>

    // 가게 개별 정보 조회(사용자)
    fun getStroreDetails(storeId: Long): List<StoreResponse>

    // 본인 가게 목록 조회
    fun getStoreByUserId(userId: Long): List<StoreListResponse>

    // 가게 생성
    fun createStore(userId: Long, request: StoreRequest): StoreResponse

    // 가게 정보 수정
    fun modifyStore(userId: Long, storeId: Long, request: StoreRequest): StoreResponse

    // 가게 영업상태 변경
    fun storeStatusChange(storeId: Long, userId: Long, request: StoreStatusRequest): StoreResponse
}