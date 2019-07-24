package io.duryskuba.interestmatcher.GroupService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

    @Id
    @Column(name = "user_id")
    private Long userId; //id from user-service

    private String username;

    @Column(name = "PROFILE_URL")
    private String profileUrl;
}
