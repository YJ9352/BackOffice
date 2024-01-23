package com.teamsparta.backoffice.domain.cart.controller

import com.teamsparta.backoffice.domain.cart.dto.AddCartMenuRequest
import com.teamsparta.backoffice.domain.cart.dto.CartResponse
import com.teamsparta.backoffice.domain.cart.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartController(
    private val cartService: CartService
) {

    @PostMapping
    fun addCartMenu(
//        @AuthenticationPrincipal user: User,
        @RequestBody addCartMenuRequest: AddCartMenuRequest
    ): ResponseEntity<CartResponse> {
        // Todo userId change
        var userId = 1L
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.addCartMenu(userId, addCartMenuRequest))
    }

    @GetMapping
    fun getCartByUserId(
//        @AuthenticationPrincipal user: User
    ): ResponseEntity<CartResponse> {
        // Todo userId change
        var userId = 1L
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.getCartByUserId(userId))
    }

    @DeleteMapping
    fun deleteCart(
//        @AuthenticationPrincipal user: User
    ): ResponseEntity<Unit> {
        // Todo userId change
        var userId = 1L
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(cartService.deleteCartByUserId(userId))
    }

    @DeleteMapping("/{cartMenuId}")
    fun deleteCartMenu(
//        @AuthenticationPrincipal user: User,
        @PathVariable cartMenuId:Long
    ): ResponseEntity<Unit> {
        // Todo userId change
        var userId = 1L
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(cartService.deleteCartMenu(userId, cartMenuId))
    }

}