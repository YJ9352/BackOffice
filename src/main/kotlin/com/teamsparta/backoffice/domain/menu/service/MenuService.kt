package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse

interface MenuService {
    fun getAllMenu(): List<MenuResponse>
    fun createMenu(request: MenuRequest): MenuResponse
    fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse
    fun menuStatusChange(menuId: Long, request: StatusRequest): MenuResponse
}