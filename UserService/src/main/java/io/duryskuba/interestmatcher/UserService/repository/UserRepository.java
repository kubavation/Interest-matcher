package io.duryskuba.interestmatcher.UserService.repository;

import io.duryskuba.interestmatcher.UserService.resource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
