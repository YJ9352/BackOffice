package com.teamsparta.backoffice.domain.menu.controller

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.MenuStatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuListResponse
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.service.MenuService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stores/{storeId}/menus")
class MenuController(
    private val menuService: MenuService
) {

    // 메뉴 전체조회
    @GetMapping()
    fun getAllMenu(
        @PathVariable storeId: Long
    ): ResponseEntity<List<MenuListResponse>> {
        val menus = menuService.getAllMenu(storeId)
        return ResponseEntity.status(HttpStatus.OK).body(menus)
    }

    // 메뉴 추가
    @PostMapping()
    fun createMenu(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: MenuRequest,
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(menuService.createMenu(storeId, userPrincipal.id, request))
    }

    // 메뉴 수정
    @PutMapping("/{menuId}")
    fun modifyMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: MenuRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.modifyMenu(menuId, userPrincipal.id, storeId, request))
    }

    // 메뉴 상태변경
    @PutMapping("/{menuId}/status")
    fun menuStatusChange(
        @PathVariable menuId: Long,
        @PathVariable storeId: Long,
        @RequestBody request: MenuStatusRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.menuStatusChange(menuId, storeId, menuId, request))
    }

}