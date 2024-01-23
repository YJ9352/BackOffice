package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.common.MenuStatus
import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.dto.response.GetAllMenuResponse
import com.teamsparta.backoffice.domain.menu.model.Menu
import com.teamsparta.backoffice.domain.menu.model.toGetAllMenuResponse
import com.teamsparta.backoffice.domain.menu.model.toMenuRespone
import com.teamsparta.backoffice.domain.menu.repository.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuServiceImpl(
    private val menuRepository: MenuRepository
) : MenuService {

    // 메뉴 전체조회
    override fun getAllMenu(): List<GetAllMenuResponse> {
        return menuRepository.findAll().map { it.toGetAllMenuResponse() }
    }

    // 메뉴 추가
    @Transactional
    override fun createMenu(request: MenuRequest): MenuResponse {
        // 추후 에러처리 추가 및 교체
        // 유저 추가 시 아이디 & 권한 확인
//        val menu = menuRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException(userId)

        return menuRepository.save(
            Menu(
                name = request.name,
                imageUrl = request.imageUrl,
                description = request.description,
                price = request.price,
                status = MenuStatus.SALE // 기본상태 판매중
            )
        ).toMenuRespone()
    }

    // 메뉴 수정
    @Transactional
    override fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse {
        // 추후 에러처리 추가 및 교체
        // 유저 추가 시 아이디 & 권한 확인
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
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw IllegalStateException("해당하는 메뉴가 없습니다.")

        // 입력방법 좀더 고민해보기
        // 내일 판매중단 상태일때 메뉴 숨김처리 추가
        menu.status = when(request.status) {
            "SALE" -> MenuStatus.SALE
            "SOLDOUT" -> MenuStatus.SOLDOUT
            "DISCONTINUED" -> MenuStatus.DISCONTINUED
            else -> throw IllegalStateException("해당하는 상태가 없습니다.")
        }

        return menuRepository.save(menu).toMenuRespone()
    }
}