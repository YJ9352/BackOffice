package com.teamsparta.backoffice.domain.store.model

import jakarta.persistence.*

@Entity
@Table(name = "stores")
class Store(

    @Column(name = "name")
    var name: String,

    @Column(name = "profileimgurl")
    var profileImgUrl: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "category")
    var category: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: StoreStatus,

){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    val id: Long = 0L

}