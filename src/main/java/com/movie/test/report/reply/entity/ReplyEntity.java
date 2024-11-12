package com.movie.test.report.reply.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "mv_reply", indexes = {@Index(name = "index_upReplyId",columnList = "upReplyId")})
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
@SQLDelete(sql = "UPDATE mv_reply SET isDeleted = true, deleteDate = now() WHERE replyId = ?")
public class ReplyEntity extends BaseTimeEntity {

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
