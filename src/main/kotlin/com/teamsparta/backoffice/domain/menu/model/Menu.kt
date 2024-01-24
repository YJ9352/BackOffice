package com.teamsparta.backoffice.domain.menu.model

import com.teamsparta.backoffice.domain.menu.common.MenuStatus
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import com.teamsparta.backoffice.domain.menu.dto.response.GetAllMenuResponse
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
//    @Column(name = "storeId")
//    val stores: Store,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    val id: Long = 0

}

fun Menu.toMenuRespone(): MenuResponse {
    return MenuResponse(
        menuId = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        price = price,
        status = status.name,
    )
}

fun Menu.toGetAllMenuResponse(): GetAllMenuResponse {
    return GetAllMenuResponse(
        menuId = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}