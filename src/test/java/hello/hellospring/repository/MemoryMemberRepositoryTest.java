package hello.hellospring.repository;

import hello.hellospring.domain.Member;
// 서로 다른 lib에 Assertions가 각각 있음. 제공 method 다름
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*; // 주로 사용
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // -> 각 Test가 끝날 때마다 수행할 함수임을 의미하는 annotation
    public void afterEach() {
        // 각 Test가 끝날 때마다 repository를 clear하지 않으면 버그 발생 가능
        // e.g., member의 name이 "spring1"로 같은 멤버가 2명. 이 때 findByName을 한다면? 버그!
        repository.clearAll();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // System.out.println("(result = member) = " + (result == member)); // soutv 입력. 쩐다..
        // Assertions.assertEquals(member, result); // org.junit.jupiter.api.Assertions
        assertThat(member).isEqualTo(result); // org.assertj.core.api.Assertions
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}