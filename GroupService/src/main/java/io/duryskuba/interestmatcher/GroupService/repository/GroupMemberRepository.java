package io.duryskuba.interestmatcher.GroupService.repository;

import io.duryskuba.interestmatcher.GroupService.resource.GroupMember;
import io.duryskuba.interestmatcher.GroupService.resource.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
}
