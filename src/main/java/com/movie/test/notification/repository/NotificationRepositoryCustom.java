package com.movie.test.notification.repository;

import com.movie.test.notification.entity.NotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NotificationRepositoryCustom {

    Slice<NotificationEntity> getNotiList(Long notificationId, Pageable pageable);
}
