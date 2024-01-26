package com.teamsparta.backoffice.domain.cart.model

import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
data class Cart(

        @OneToOne
        @JoinColumn(name = "user_id")
        val user: User,
        // Todo @ManyToOne
        val storeId: Long

) {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
