package com.movie.test.notification.service;

import com.movie.test.notification.dto.NotificationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NotificationService {

    NotificationDTO registNoti(NotificationDTO notificationDTO);

    Long modifyNoti(NotificationDTO notificationDTO);

    void deleteNoti(Long notificationId);

    NotificationDTO getNoti(Long notificationId);

    Slice<NotificationDTO> getNotiList(Long notificationId, Pageable pageable);
}
