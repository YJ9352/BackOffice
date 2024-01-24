package com.teamsparta.backoffice.infra.audit

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseUserEntity : BaseTimeEntity() {
    @CreatedBy
    @Column(nullable = true, updatable = true)
    var createdId: Long? = null

    @LastModifiedBy
    @Column(nullable = true, updatable = true)
    var modifiedId: Long? = null
}
/*
데이터의 생성 시간을 추적할 수 있는 기능
 */