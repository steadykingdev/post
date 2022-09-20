package com.steadykingdev.post.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private final AddMemberService addMemberService;
    private static long sequence = 0L;

    public Member save(AddMemberForm addMemberForm) {
        Member member = addMemberService.addMember(addMemberForm.getLoginId(), addMemberForm.getPassword(), addMemberForm.getNickName());
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

}
