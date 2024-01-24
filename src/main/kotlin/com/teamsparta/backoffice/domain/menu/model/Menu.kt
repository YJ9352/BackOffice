package com.teamsparta.backoffice.domain.menu.model

import com.teamsparta.backoffice.domain.menu.dto.response.MenuListResponse
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import jakarta.persistence.*

@Entity
@Table(name = "menus")
class Menu(

        @Column(name = "name")
        var name: String,

        @Column(name = "imageurl")
        var imageUrl: String,

        @Column(name = "description")
        var description: String,

        @Column(name = "price")
        var price: Int,

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        var status: MenuStatus,

//    @ManyToOne
        @JoinColumn(name = "store_id")
        val storeId: Long,

//    @ManyToOne
//    @Column(name = "user_id")
//    val userId: Long,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    val id: Long = 0L

}

fun Menu.toMenuRespone(): MenuResponse {
    return MenuResponse(
            menuId = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            price = price,
            status = status.name
    )
}

fun Menu.toMenuListResponse(): MenuListResponse {
    return MenuListResponse(
            menuId = id,
            name = name,
            price = price,
            imageUrl = imageUrl
    )
}