package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.common.MenuStatus
import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuListResponse
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.model.Menu
import com.teamsparta.backoffice.domain.menu.model.toMenuListResponse
import com.teamsparta.backoffice.domain.menu.model.toMenuRespone
import com.teamsparta.backoffice.domain.menu.repository.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuServiceImpl(
//    private val storeRepository: StoreRepository,
//    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository
) : MenuService {

    // 메뉴 전체조회
    override fun getAllMenu(): List<MenuListResponse> {
        return menuRepository.findAll().map { it.toMenuListResponse() }
    }

    // 메뉴 추가
    @Transactional
    override fun createMenu(request: MenuRequest): MenuResponse {
        // 이용자 확인
//      val user = userRepository.findByEmail(email) ?: IllegalStateException("이용권한이 없습니다.")

        return menuRepository.save(
            Menu(
                name = request.name,
                imageUrl = request.imageUrl,
                description = request.description,
                price = request.price,
                status = MenuStatus.SALE, // 기본상태 판매중
//                user = User,
//                store = Store
            )
        ).toMenuRespone()
    }

    // 메뉴 수정
    @Transactional
    override fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse {
        // 수정할 메뉴가 있는지 확인
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw IllegalStateException("해당하는 메뉴가 없습니다.")

        menu.name = request.name
        menu.imageUrl = request.imageUrl
        menu.description = request.description
        menu.price = request.price

        return menuRepository.save(menu).toMenuRespone()
    }

    // 메뉴 상태변경
    @Transactional
    override fun menuStatusChange(menuId: Long, storeId: Long, request: StatusRequest): MenuResponse {
        // 해당 메뉴가 존재하는지 확인
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw IllegalStateException("해당하는 메뉴가 없습니다.")

        menu.status = when (request.status) {
            "SALE" -> MenuStatus.SALE
            "SOLDOUT" -> MenuStatus.SOLDOUT
            "DISCONTINUED" -> MenuStatus.DISCONTINUED
            else -> throw IllegalStateException("해당하는 상태가 없습니다.")
        }

        return menuRepository.save(menu).toMenuRespone()
    }
}
