package io.duryskuba.interestmatcher.GroupService.repository;

import io.duryskuba.interestmatcher.GroupService.resource.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
