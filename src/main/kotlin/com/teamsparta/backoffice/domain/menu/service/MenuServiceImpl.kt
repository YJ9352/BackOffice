package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.exception.StringNotFoundException
import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.MenuStatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuListResponse
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.model.Menu
import com.teamsparta.backoffice.domain.menu.model.MenuStatus
import com.teamsparta.backoffice.domain.menu.model.toMenuListResponse
import com.teamsparta.backoffice.domain.menu.model.toMenuResponse
import com.teamsparta.backoffice.domain.menu.repository.MenuRepository
import com.teamsparta.backoffice.domain.store.repository.StoreRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuServiceImpl(
    private val menuRepository: MenuRepository,
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository
) : MenuService {

    // 메뉴 전체조회
    override fun getAllMenu(storeId: Long): List<MenuListResponse> {
        menuRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)
        return menuRepository.findByStoreId(storeId).map { it.toMenuListResponse() }
    }

    // 메뉴 추가
    @Transactional
    override fun createMenu(storeId: Long, userId: Long, request: MenuRequest): MenuResponse {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("storeId", storeId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId", userId)

        if (store.user.id != userId) {
            throw AccessDeniedException("본인이 개설한 가게에만 메뉴를 추가할 수 있습니다.")
        }
        return menuRepository.save(
            Menu(
                name = request.name,
                imageUrl = request.imageUrl,
                description = request.description,
                price = request.price,
                status = MenuStatus.SALE, // 기본상태 판매중
                user = user,
                store = store
            )
        ).toMenuResponse()
    }

    // 메뉴 수정
    @Transactional
    override fun modifyMenu(menuId: Long, userId: Long, storeId: Long, request: MenuRequest): MenuResponse {
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw ModelNotFoundException("menuId", menuId)
        if (menu.user.id != userId || menu.id != menuId || menu.store.id != storeId) throw AccessDeniedException("메뉴를 수정할 권한이 없습니다.")

        menu.name = request.name
        menu.imageUrl = request.imageUrl
        menu.description = request.description
        menu.price = request.price

        return menuRepository.save(menu).toMenuResponse()
    }

    // 메뉴 상태변경
    @Transactional
    override fun menuStatusChange(menuId: Long, storeId: Long, userId: Long, request: MenuStatusRequest): MenuResponse {
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw ModelNotFoundException("menuId", menuId)

        menu.status = when (request.status) {
            "SALE" ->
                if (menu.user.id == userId) MenuStatus.SALE
                else throw ModelNotFoundException("userId", userId)

            "SOLD_OUT" ->
                if (menu.user.id == userId) MenuStatus.SOLD_OUT
                else throw ModelNotFoundException("userId", userId)

            "DISCONTINUED" ->
                if (menu.user.id == userId) MenuStatus.DISCONTINUED
                else throw ModelNotFoundException("userId", userId)

            else -> throw StringNotFoundException("status", request.status)
        }

        return menuRepository.save(menu).toMenuResponse()
    }
}
