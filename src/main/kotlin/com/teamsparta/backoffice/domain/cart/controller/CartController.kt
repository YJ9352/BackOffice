package com.teamsparta.backoffice.domain.cart.controller

import com.teamsparta.backoffice.domain.cart.dto.AddCartMenuRequest
import com.teamsparta.backoffice.domain.cart.dto.CartResponse
import com.teamsparta.backoffice.domain.cart.service.CartService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartController(
    private val cartService: CartService
) {

    @PostMapping
    fun addCartMenu(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody addCartMenuRequest: AddCartMenuRequest
    ): ResponseEntity<CartResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.addCartMenu(userPrincipal.id, addCartMenuRequest))
    }

    @GetMapping
    fun getMyCart(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CartResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.getCartByUserId(userPrincipal.id))
    }

    @DeleteMapping
    fun deleteCart(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(cartService.deleteCartByUserId(userPrincipal.id))
    }

    @DeleteMapping("/{cartMenuId}")
    fun deleteCartMenu(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cartMenuId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(cartService.deleteCartMenu(userPrincipal.id, cartMenuId))
    }
}