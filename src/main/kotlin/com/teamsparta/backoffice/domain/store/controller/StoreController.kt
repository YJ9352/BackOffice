package com.teamsparta.backoffice.domain.store.controller

import com.teamsparta.backoffice.domain.store.dto.request.StoreRequest
import com.teamsparta.backoffice.domain.store.dto.request.StoreStatusRequest
import com.teamsparta.backoffice.domain.store.dto.response.StoreListResponse
import com.teamsparta.backoffice.domain.store.dto.response.StoreResponse
import com.teamsparta.backoffice.domain.store.service.StoreService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stores")
class StoreController(
    private val storeService: StoreService
) {

    // 본인 가게 목록 조회
    @GetMapping()
    fun getStoreByUserId(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<StoreListResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getStoreByUserId(userPrincipal.id))
    }

    // 가게 생성
    @PostMapping()
    fun createStore(
        @RequestBody request: StoreRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(storeService.createStore(userPrincipal.id, request))
    }

    // 가게 정보 수정
    @PutMapping("/{storeId}")
    fun modifyStore(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: StoreRequest
    ): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.modifyStore(storeId, userPrincipal.id, request))
    }

    // 가게 영업상태 변경
    @PutMapping("/{storeId}/status")
    fun storeStatusChange(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: StoreStatusRequest
    ): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.storeStatusChange(storeId, userPrincipal.id, request))
    }
}