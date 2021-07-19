package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    // client > form > name="name"으로 post 방식으로 변수가 오면, spring이 setName 사용
    public void setName(String name) {
        this.name = name;
    }
}
