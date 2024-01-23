package com.teamsparta.backoffice.domain.cart.model

import jakarta.persistence.*

@Entity
data class Cart(

    // Todo @OneToOne
    val userId: Long,
    // Todo @ManyToOne
    val storeId: Long

) {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
