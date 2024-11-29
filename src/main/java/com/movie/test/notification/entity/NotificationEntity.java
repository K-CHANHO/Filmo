package com.movie.test.notification.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "mv_notification")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@SQLDelete(sql = "UPDATE mv_notification SET isDeleted = true, deleteDate = now() WHERE notificationId = ?")
@Where(clause = "isDeleted is null || isDeleted = 0")
public class NotificationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String type;

}
