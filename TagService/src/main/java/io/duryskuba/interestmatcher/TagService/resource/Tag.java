package io.duryskuba.interestmatcher.TagService.resource;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAG")
@Data
@NoArgsConstructor
public class Tag {

    @Id
    private String name;

    @CreationTimestamp
    //@Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
