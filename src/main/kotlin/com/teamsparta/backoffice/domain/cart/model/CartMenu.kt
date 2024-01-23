package com.teamsparta.backoffice.domain.cart.model

import jakarta.persistence.*

data class CartMenu(
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    var cart: Cart,

    // Todo @ManyToOne
    var menuId: Long,

    @Column(name = "cart_menu_quantity")
    var quantity : Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
