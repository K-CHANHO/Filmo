package com.movie.test.notification.repository;

import com.movie.test.notification.entity.NotificationEntity;
import com.movie.test.notification.entity.QNotificationEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    private QNotificationEntity notification = QNotificationEntity.notificationEntity;

    @Override
    public Slice<NotificationEntity> getNotiList(Long notificationId, Pageable pageable) {

        List<NotificationEntity> entityList = jpaQueryFactory.selectFrom(notification)
                .where(
                        checkNotificationId(notificationId)
                )
                .orderBy(notification.notificationId.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(entityList.size() > pageable.getPageSize()){
            entityList.remove(pageable.getPageSize());
            hasNext = true;
        }

        SliceImpl<NotificationEntity> notificationEntities = new SliceImpl<>(entityList, pageable, hasNext);

        return notificationEntities;
    }

    private BooleanExpression checkNotificationId(Long notificationId) {
        return notificationId != null ? notification.notificationId.lt(notificationId) : null;
    }
}
