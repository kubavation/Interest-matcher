package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAG_SUBSCRIBER")
@Data
@NoArgsConstructor
public class TagSubscriber {

    @EmbeddedId
    private TagSubscriberId id;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public TagSubscriber(TagSubscriberId id) {
        this.id = id;
    }
}
