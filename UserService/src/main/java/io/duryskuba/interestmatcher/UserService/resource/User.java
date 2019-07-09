package io.duryskuba.interestmatcher.UserService.resource;

import io.duryskuba.interestmatcher.UserService.enums.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;



}
