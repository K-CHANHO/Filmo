package com.movie.test.reply.entity;

import com.movie.test.common.entity.baseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "reply")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class replyEntity extends baseTimeEntity {

    @Id
    @Column
    private String replyId; // 댓글 고유 id

    @Column
    private String upReplyId; // 대댓글인 경우 원댓글 id

    @Column
    private String reportId; // 감상문 id

    @Column
    private String userId; // 댓글 작성자 id

    @Column
    private String content; // 댓글내용

}
