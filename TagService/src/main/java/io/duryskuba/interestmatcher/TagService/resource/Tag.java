package io.duryskuba.interestmatcher.TagService.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAG")
@Data
@NoArgsConstructor
public class Tag {

    @Id
    private String name;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
