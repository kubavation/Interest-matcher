package io.duryskuba.interestmatcher.GroupService.repository;

import io.duryskuba.interestmatcher.GroupService.resource.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
