package prs.midwit.linknote.member.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prs.midwit.linknote.member.dto.req.MemberSignupRequest;
import prs.midwit.linknote.member.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 신규회원 가입시에 활용되는 메소드
    @PostMapping("/signup")
    public ResponseEntity<Void> memberSignUp(@RequestBody  MemberSignupRequest request) {
        memberService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
