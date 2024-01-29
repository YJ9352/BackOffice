package com.teamsparta.backoffice.domain.cart.model

import com.teamsparta.backoffice.domain.menu.model.Menu
import jakarta.persistence.*

@Entity
data class CartMenu(
        @ManyToOne
        @JoinColumn(name = "cart_id")
        var cart: Cart,

        @ManyToOne
        @JoinColumn(name = "menu_id")
        var menu: Menu,

        @Column(name = "cart_menu_quantity")
        var quantity: Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
