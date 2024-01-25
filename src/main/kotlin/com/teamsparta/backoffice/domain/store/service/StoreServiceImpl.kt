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
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    private val storeRepository: StoreRepository,
    private val userRepository: UserRepository,
) : StoreService {

    // 본인 가게 목록 조회
    override fun getStoreByUserId(userId: Long): List<StoreListResponse> {
        storeRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId", userId)
        return storeRepository.findByUserId(userId).map { it.toStoreListResponse() }
    }

    // 가게 생성
    @Transactional
    override fun createStore(userId: Long, request: StoreRequest): StoreResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId", userId)
        return storeRepository.save(
            Store(
                user = user,
                name = request.name,
                profileImgUrl = request.profileImgUrl,
                category = request.category,
                address = request.address,
                phone = request.phone,
                description = request.description,
                status = StoreStatus.OPEN
            )

        ).toStoreResponse()
    }

    // 가게 정보 수정
    override fun modifyStore(userId: Long, storeId: Long, request: StoreRequest): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)
        if (store.user.id == userId) {
            val (name, profileImgUrl, category, address, phone, description) = request

            store.name = name
            store.profileImgUrl = profileImgUrl
            store.category = category
            store.address = address
            store.phone = phone
            store.description = description
            return storeRepository.save(store).toStoreResponse()
        } else {
            throw AccessDeniedException("본인의 가게만 수정 가능합니다.")
        }
    }

    // 가게 영업상태 변경
    override fun storeStatusChange(storeId: Long, userId: Long, request: StoreStatusRequest): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)

        store.status = when (request.status) {
            "OPEN" -> if (store.user.id == userId)
                StoreStatus.OPEN else throw ModelNotFoundException("userId", userId)
            "CLOSED" -> if (store.user.id == userId)
                StoreStatus.CLOSED else throw ModelNotFoundException("userId",userId)
            else -> throw StringNotFoundException("status", request.status)
        }

        return storeRepository.save(store).toStoreResponse()
    }
}