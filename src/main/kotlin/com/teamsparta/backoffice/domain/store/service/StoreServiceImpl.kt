package com.teamsparta.backoffice.domain.store.service

import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.exception.StringNotFoundException
import com.teamsparta.backoffice.domain.store.dto.request.StoreRequest
import com.teamsparta.backoffice.domain.store.dto.request.StoreStatusRequest
import com.teamsparta.backoffice.domain.store.dto.response.StoreListResponse
import com.teamsparta.backoffice.domain.store.dto.response.StoreResponse
import com.teamsparta.backoffice.domain.store.model.Store
import com.teamsparta.backoffice.domain.store.model.StoreStatus
import com.teamsparta.backoffice.domain.store.model.toStoreListResponse
import com.teamsparta.backoffice.domain.store.model.toStoreResponse
import com.teamsparta.backoffice.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    private val storeRepository: StoreRepository
) : StoreService {

    // 본인 가게 목록 조회
    override fun getStoreByUserId(userId: Long): List<StoreListResponse> {
        storeRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId", userId)
        return storeRepository.findByUserId(userId).map { it.toStoreListResponse() }
    }

    // 가게 생성
    @Transactional
    override fun createStore(userId: Long, request: StoreRequest): StoreResponse {
        storeRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId", userId)
        return storeRepository.save(
            Store(
                userId = userId,
                name = request.name,
                profileImgUrl = request.profileImgUrl,
                category = request.category,
                address = request.address,
                phone = request.address,
                description = request.description,
                status = StoreStatus.OPEN,
            )

        ).toStoreResponse()
    }

    // 가게 정보 수정
    override fun modifyStore(userId: Long, storeId: Long, request: StoreRequest): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)
        if (store.userId != userId) throw AccessDeniedException("권한이 없습니다. 로그인 아이디를 확인해주세요.")

        store.name = request.name
        store.profileImgUrl = request.profileImgUrl
        store.category = request.category
        store.address = request.address
        store.phone = request.phone
        store.description = request.description

        return storeRepository.save(store).toStoreResponse()
    }

    // 가게 영업상태 변경
    override fun storeStatusChange(storeId: Long, userId: Long, request: StoreStatusRequest): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)

        store.status = when (request.status) {
            "OPEN" -> if (store.userId == userId)
                StoreStatus.OPEN else throw ModelNotFoundException("userId", userId)
            "CLOSED" -> if (store.userId == userId)
                StoreStatus.CLOSED else throw ModelNotFoundException("userId",userId)

            else -> throw StringNotFoundException("status", request.status)
        }

        return storeRepository.save(store).toStoreResponse()
    }
}