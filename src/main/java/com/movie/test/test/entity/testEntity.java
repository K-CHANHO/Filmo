package com.movie.test.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="test")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class testEntity {

    @Id
    @Column
    private String param1;

    @Column
    private String param2;

}
