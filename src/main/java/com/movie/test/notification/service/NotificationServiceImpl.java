package com.movie.test.notification.service;

import com.movie.test.notification.dto.NotificationDTO;
import com.movie.test.notification.entity.NotificationEntity;
import com.movie.test.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationDTO registNoti(NotificationDTO notificationDTO) {

        NotificationEntity savedNoti = notificationRepository.save(NotificationDTO.toEntity(notificationDTO));

        return NotificationDTO.toDTO(savedNoti);
    }

    @Override
    public Long modifyNoti(NotificationDTO notificationDTO) {
        NotificationEntity originEntity = notificationRepository.findById(notificationDTO.getNotificationId()).get();
        NotificationEntity buildedEntity = originEntity.toBuilder()
                .title(notificationDTO.getTitle())
                .content(notificationDTO.getContent())
                .type(notificationDTO.getType())
                .build();

        notificationRepository.save(buildedEntity);

        return notificationDTO.getNotificationId();
    }

    @Override
    public void deleteNoti(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public NotificationDTO getNoti(Long notificationId) {
        NotificationEntity notiEntity = notificationRepository.findById(notificationId).get();

        return NotificationDTO.toDTO(notiEntity);
    }

    @Override
    public Slice<NotificationDTO> getNotiList(Long notificationId, Pageable pageable) {

        Slice<NotificationEntity> notiEntityList = notificationRepository.getNotiList(notificationId, pageable);
        Slice<NotificationDTO> notiList = notiEntityList.map(NotificationDTO::toDTO);

        return notiList;
    }
}
