package com.teamsparta.backoffice.domain.menu.service

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.MenuStatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuListResponse
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse

interface MenuService {

    // 메뉴 전체조회
    fun getAllMenu(storeId: Long): List<MenuListResponse>

    // 메뉴 추가
    fun createMenu(userId: Long, storeId: Long, request: MenuRequest): MenuResponse

    // 메뉴 수정
    fun modifyMenu(menuId: Long, request: MenuRequest): MenuResponse

    // 메뉴 상태변경
    fun menuStatusChange(menuId: Long, storeId: Long, request: MenuStatusRequest): MenuResponse
}