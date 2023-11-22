package com.movie.test.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class baseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private Timestamp createDate;

    @LastModifiedDate
    @Column
    private Timestamp lastModifiedDate;
}
