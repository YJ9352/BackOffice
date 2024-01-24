package com.teamsparta.backoffice.domain.order.model

import jakarta.persistence.*


@Entity
class OrderMenu(
    // TODO @ManyToOne menu
    var menuId : Long,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order,

    @Column(name = "order_menu_quantity")
    var quantity:Int

    ) {
    @Id
    @Column(name = "order_menu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}