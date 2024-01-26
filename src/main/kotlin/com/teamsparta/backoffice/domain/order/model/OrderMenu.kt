package com.teamsparta.backoffice.domain.order.model

import com.teamsparta.backoffice.domain.menu.model.Menu
import jakarta.persistence.*


@Entity
@Table(name = "order_menu")
class OrderMenu(
    @ManyToOne
    @JoinColumn(name = "menu_id")
    var menu: Menu,

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,

    @Column(name = "order_menu_quantity")
    var quantity: Int

) {
    @Id
    @Column(name = "order_menu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}