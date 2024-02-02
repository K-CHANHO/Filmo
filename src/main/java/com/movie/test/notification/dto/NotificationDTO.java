package com.movie.test.notification.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.notification.entity.NotificationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO extends BaseTimeDTO {

    private Long notificationId;
    private String title;
    private String content;
    private String type;

    public static NotificationEntity toEntity(NotificationDTO dto) {

        NotificationEntity entity = NotificationEntity.builder()
                .notificationId(dto.getNotificationId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .type(dto.getType())
                .build();

        return entity;
    }

    public static NotificationDTO toDTO(NotificationEntity entity){

        NotificationDTO dto = NotificationDTO.builder()
                .notificationId(entity.getNotificationId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

}
