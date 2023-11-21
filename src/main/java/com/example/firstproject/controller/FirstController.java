package com.example.firstproject.controller;

// 컨트롤러 선언과 동시에 자동으로 임포트 됨.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// 이 클래스가 컨트롤러임을 선언하는 @Controller 어노테이션 작성한다. 이렇게 하면 Controller 클래스 패키지
// 가 자동으로 임포트 된다. 위에 있음.
public class FirstController {
    // 반환형이 문자열인 niceToMeetYou() 메서드를 선언한다.

    //페이지(greetings.mustache)를 반환해 달라는 URL 요청을 접수하는 부분이다. -> @GetMapping
    //niceToMeetYou()메서드 앞에 @GetMapping을 추가한다. 그러면 자동으로 겟매핑 패키지가 임포트 된다.
    //겟매핑 주소안에는 /hi 주소를 넣어준다. @GetMapping("/hi")
    @GetMapping("/hi")
    // niceToMeetYou() 메서드에 Model 타입의 model 매개 변수를 추가
    public String niceToMeetYou(Model model) {

        // 그 다음 Model 클래스 패키지가 임포트 됐다면, 모델을 통해 변수를 등록 할 수 있다.
        // 모델에서 변수를 등록할 때는 addAttribute() 메서드를 사용.
        // 형식 model.addAttribute("변수명", "변수값"); // 변수값을 "변수명"이라는 이름으로 추가

        model.addAttribute("username", "김대중");
        // niceToMeetYou() 메서드 내부에 model.addAttribute("username", "홍어삼합"); 이라는 코드를 추가한다.
        // 서버 내부에서 username이라는 변수를 찾을 수 없어 에러가 발생 했으므로, "username"이라는 이름을 등록하고,
        // "홍어삼합"이라는 값을 넣어준것.

        // 그리고 공백 문자열("")을 반환 하도록 return ""; 문을 추가한다. 이 반환문을 이용해 앞에서 만든
        // greetings.mustache 페이지를 반환하겠다. greetings.mustache 페이지를 반환하려면 파일 이름인 greetings
        // 만 반환값으로 적어주면 된다. 즉 return "greetings";로 적어 주면 서버가 알아서 templates 디렉토리에서
        // greetings.mustache 파일을 찾아 웹 브라우저로 전송한다.
        return "greetings"; // greetings.mustache 파일 반환
    }

    // localhost:8080/bye
    @GetMapping("/bye")
    // 컨트롤러 안에 /bye 요청을 처리할 seeYouNext()메서드를 만들었다. 그리고 매개변수로 model 객체를 받아옴.
    public String SeeYouNext(Model model) {

        // 등록할 변수명과 변숫값을 적어준다. model -> key , value
        model.addAttribute("nickname", "홍길동");

        return "goodbye"; // goodbye.mustache 파일 반환
    }
}
