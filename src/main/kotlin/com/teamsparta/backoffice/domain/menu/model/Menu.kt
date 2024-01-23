package com.teamsparta.backoffice.domain.menu.model

import com.teamsparta.backoffice.domain.menu.common.MenuStatus
import com.teamsparta.backoffice.domain.menu.dto.response.MenuResponse
import jakarta.persistence.*

@Entity
@Table(name = "menus")
class Menu(

    @Column(name = "name")
    var name: String,

    @Column(name = "imageUrl")
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
    @Column(name = "menuid")
    val menuId: Long = 0

}

fun Menu.toMenuRespone(): MenuResponse {
    return MenuResponse(
        menuId = menuId,
        name = name,
        imageUrl = imageUrl,
        description = description,
        price = price,
        status = status.name,
    )
}