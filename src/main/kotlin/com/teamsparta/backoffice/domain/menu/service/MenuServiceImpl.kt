package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuServiceImpl(
    private val menuRepository: MenuRepository
): MenuService {
    override fun getAllMenu(): List<MenuResponse> {
        TODO("Not yet implemented")
    }

    override fun createMenu(request: MenuRequest): MenuResponse {
        TODO("Not yet implemented")
    }

    override fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse {
        TODO("Not yet implemented")
    }

    override fun menuStatusChange(menuId: Long, request: StatusRequest): MenuResponse {
        TODO("Not yet implemented")
    }

}