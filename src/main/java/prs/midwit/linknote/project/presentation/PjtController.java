package prs.midwit.linknote.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prs.midwit.linknote.member.service.MemberService;
import prs.midwit.linknote.project.dto.res.PjtsResponse;
import prs.midwit.linknote.project.service.PjtService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PjtController {
    private final MemberService memberService;
    private final PjtService pjtService;

    //자신이 작성한 프로젝트를 조회
    @GetMapping("/pjts")
    public ResponseEntity<PjtsResponse> getProjects(
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        String userId = userDetails.getUsername();
        Long memberCode = memberService.getMemberCodeByName(userId);

        PjtsResponse res = pjtService.getPjtsByMemberCode(memberCode);

        return ResponseEntity.ok(res);
    }
}
