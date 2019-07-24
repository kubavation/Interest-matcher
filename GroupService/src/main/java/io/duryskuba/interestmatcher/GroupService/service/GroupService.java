package io.duryskuba.interestmatcher.GroupService.service;

import io.duryskuba.interestmatcher.GroupService.repository.GroupMemberRepository;
import io.duryskuba.interestmatcher.GroupService.repository.GroupRepository;
import io.duryskuba.interestmatcher.GroupService.repository.MemberRepository;
import io.duryskuba.interestmatcher.GroupService.resource.Group;
import io.duryskuba.interestmatcher.GroupService.resource.GroupMember;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private MemberRepository memberRepository;
    private GroupMemberRepository groupMemberRepository;

    public GroupService(GroupRepository groupRepository,
                        MemberRepository memberRepository,
                        GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.groupMemberRepository = groupMemberRepository;
    }


    public Group findGroupById(Long id) {
        return groupRepository.findById(id);
    }
}
