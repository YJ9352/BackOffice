package com.teamsparta.backoffice.domain.order.model

import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "`order`")
class Order(
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    var status: OrderStatus = OrderStatus.CONFIRM_WAIT,

    @Column(name = "order_total_pay")
    var totalPay : Int,

    @Column(name = "order_payment_time")
    var paymentTime : LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User,

    // TODO store
    // @ManyToOne
    var storeId : Long

) {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}