package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.dto.response.GetAllMenuResponse

interface MenuService {

    // 메뉴 전체조회
    fun getAllMenu(): List<GetAllMenuResponse>

    // 메뉴 추가
    fun createMenu(request: MenuRequest): MenuResponse

    // 메뉴 수정
    fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse

    // 메뉴 상태변경
    fun menuStatusChange(menuId: Long, storeId: Long, request: StatusRequest): MenuResponse
}