package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// interface implements할 때 option + enter치면 쉽게 할 수 있음
@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는, 아래 두 줄은 동시성 문제를 고려해야 함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0L = 초기값이 0이고, type이 Long임을 의미

    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store.values()가 Members
    }

    public void clearAll() {
        store.clear();
    }
}
