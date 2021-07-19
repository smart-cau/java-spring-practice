package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA로 관리할 것임을 명시
public class Member {


    // @Id = PK(Primary Key) Mapping
    // @GeneratedValue(...) = 이 PK 값은 IDENTITY 전략으로 생성되고 있음을 명시
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 회원 시스템 ID

    private String name; // 회원 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
