package com.teamsparta.backoffice.domain.cart.service

import com.teamsparta.backoffice.domain.cart.dto.AddCartMenuRequest
import com.teamsparta.backoffice.domain.cart.dto.CartResponse
import com.teamsparta.backoffice.domain.cart.model.Cart
import com.teamsparta.backoffice.domain.cart.model.CartMenu
import com.teamsparta.backoffice.domain.cart.repository.CartMenuRepository
import com.teamsparta.backoffice.domain.cart.repository.CartRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartMenuRepository: CartMenuRepository,
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
    // Todo  private val menuRepository: MenuRepository
) {
    private fun createCart(userId: Long, storeId: Long): Cart {
        // Todo Model Not Found Exception
        val user = userRepository.findByIdOrNull(userId)?: throw RuntimeException()

        return cartRepository.save(
            Cart(
                user = user,
                storeId = storeId
            )
        )
    }

    fun addCartMenu(userId: Long, addCartMenuRequest: AddCartMenuRequest): CartResponse {

        // userId로 생성된 cart가 있는지 확인. 없다면 생성
        val cart = cartRepository.findByUserIdOrNull(userId) ?: createCart(
            userId = userId,
            storeId = addCartMenuRequest.storeId
        )

        // Todo cart의 storeId와 새로 들어갈 menu의 storeId가 같은지 비교. 다르면 exception
        // val findByIdMenu = menuRepository.findById(addCartMenuRequest.menuId)
        var menuId = 1L
//        if (findByIdMenu.storeId != cart.storeId){
//            throw IllegalStateException()
//        }

        cartMenuRepository.save(
            CartMenu(
                cart,
                menuId,
                addCartMenuRequest.count
            )
        )
        val cartMenuList = cartMenuRepository.findByCartId(cart.id!!).map { it.menuId }
        return CartResponse(cart.id!!, userId, cartMenuList)
    }

    fun getCartByUserId(userId: Long): CartResponse? {
        val cart = cartRepository.findByUserIdOrNull(userId)
//            ?: ModelNotFoundException
            ?: throw RuntimeException()

        val cartMenuList = cartMenuRepository.findByCartId(cart.id!!).map { it.menuId }
        return CartResponse(cart.id!!, userId, cartMenuList)
    }

    fun deleteCartByUserId(userId: Long) {
        return cartRepository.deleteByUserId(userId)
    }

    fun deleteCartMenu(userId: Long, cartMenuId:Long){
        // Todo AccessDeniedException
        // Todo ModelNotFoundException
//        if(cartRepository.findById(cartMenuId).get().userId != userId) throw AccessDeniedException

        return cartMenuRepository.deleteById(cartMenuId)
    }
}