package com.teamsparta.backoffice.domain.menu.controller

import com.teamsparta.backoffice.domain.menu.dto.request.MenuRequest
import com.teamsparta.backoffice.domain.menu.dto.request.StatusRequest
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.dto.response.GetAllMenuResponse
import com.teamsparta.backoffice.domain.menu.service.MenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stores/{storeId}")
class MenuController(
    private val menuService: MenuService
) {

    // 메뉴 전체조회
    @GetMapping("/menus")
    fun getAllMenu(@PathVariable storeId: String): ResponseEntity<List<GetAllMenuResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllMenu())
    }

    // 메뉴 추가
    @PostMapping("/menus/{menuId}")
    fun createMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @RequestBody request: MenuRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(menuService.createMenu(request))
    }

    // 메뉴 수정
    @PutMapping("/menus/{menuId}")
    fun modifyMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @RequestBody request: MenuRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.modifyMenu(menuId, request))
    }

    // 메뉴 상태변경
    @PutMapping("/menus/{menuId}/status")
    fun menuStatusChange(
        @PathVariable menuId: Long,
        @PathVariable storeId: Long,
        @RequestBody request: StatusRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.menuStatusChange(menuId, storeId, request))
    }

}