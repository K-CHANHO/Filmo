package com.movie.test.hashtag.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@DynamicUpdate
@Table(name = "Tags")
public class TagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long tagId;

    @Column
    private String content;
}
