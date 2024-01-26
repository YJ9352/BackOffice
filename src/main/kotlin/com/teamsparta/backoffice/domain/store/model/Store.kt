package com.teamsparta.backoffice.domain.store.model

import com.teamsparta.backoffice.domain.review.model.Review
import com.teamsparta.backoffice.domain.review.model.toResponse
import com.teamsparta.backoffice.domain.store.dto.response.StoreListResponse
import com.teamsparta.backoffice.domain.store.dto.response.StoreResponse
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

//    @ManyToOne
    @JoinColumn(name = "user_id")
    val userId: Long,

    @OneToMany(mappedBy = "store", cascade = [CascadeType.ALL])
    var reviews: MutableList<Review> = mutableListOf()

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    val id: Long = 0L

}

fun Store.toStoreResponse(): StoreResponse {
    return StoreResponse(
        storeId = id,
        name = name,
        profileImgUrl = profileImgUrl,
        category = category,
        address = address,
        phone = phone,
        description = description,
        reviews = reviews.map { it.toResponse() }
    )
}


fun Store.toStoreListResponse(): StoreListResponse {
    return StoreListResponse(
        storeId = id,
        name = name,
        category = category,
        address = address,
        phone = phone,
        description = description
    )
}