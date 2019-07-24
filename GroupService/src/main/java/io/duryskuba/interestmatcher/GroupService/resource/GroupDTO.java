package io.duryskuba.interestmatcher.GroupService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private Long groupId;
    private String name;
    private String description;
    private Long membersCount;
    private LocalDateTime createdAt;
}
