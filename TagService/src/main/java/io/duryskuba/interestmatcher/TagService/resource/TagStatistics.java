package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAG_STATISTICS")
@Data
@NoArgsConstructor
public class TagStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TAG_NAME")
    private String tagName;

    @Column(name = "NUMBER_OF_USES")
    private Long numberOfUses;

    @Column(name="BEGIN_DATE")
    private LocalDateTime beginDate;

    @Column(name="END_DATE")
    private LocalDateTime endDate;


}
