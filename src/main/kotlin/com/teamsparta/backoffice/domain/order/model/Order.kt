package com.teamsparta.backoffice.domain.order.model

import com.teamsparta.backoffice.domain.store.model.Store
import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "`order`")
class Order(
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    var status: OrderStatus = OrderStatus.CONFIRM_WAIT,

    @Column(name = "order_total_pay")
    var totalPay: Int,

    @Column(name = "order_phone")
    val phone: String,

    @Column(name = "order_address")
    val address: String,

    @CreationTimestamp
    @Column(name = "order_payment_time", nullable = true, updatable = false)
    var paymentTime: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "store_id")
    var store: Store,
) {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}