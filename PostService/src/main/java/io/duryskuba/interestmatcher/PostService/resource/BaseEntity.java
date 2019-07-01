package io.duryskuba.interestmatcher.PostService.resource;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
abstract class BaseEntity {

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;
}
