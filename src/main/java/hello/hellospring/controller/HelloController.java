package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Controller임을 명시. 꼭 필요
public class HelloController {
    @GetMapping("hello") // GET 방식으로 /hello 요청을 받았을 때를 의미
    public String hello(Model model) {
        model.addAttribute("data", "spring!!"); // hello.html > th:text에서 사용
        return "hello"; // 자동으로 resources/templates/hello.html에 접근
    }

    @GetMapping("hello-mvc")
    public String StringhelloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http protocol의 body에 return의 내용을 직접 넣겠다는 뜻
    public  String helloString(@RequestParam("name") String name) {
        return "hello " + name; // @ResponseBody + 걍 문자 -> 걍 문자 return
    }

    @GetMapping("hello-api")
    @ResponseBody
    public  Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // @ResponseBody + 객체 return -> JSON return!
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
