package prs.midwit.linknote.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import prs.midwit.linknote.member.service.MemberService;

import prs.midwit.linknote.project.dto.req.PjtCreateRequest;
import prs.midwit.linknote.project.dto.res.PjtsResponse;
import prs.midwit.linknote.project.service.PjtService;

import java.net.URI;

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


    @PostMapping("/pjts")
    public ResponseEntity<Void> saveProject(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PjtCreateRequest request
            ) {
        String userId = userDetails.getUsername();
        Long memberCode = memberService.getMemberCodeByName(userId);

        final long pjtCode = pjtService.save(request.getPjtName(), memberCode);

        return ResponseEntity.created(URI.create("/api/v1/pjts/" + pjtCode)).build();
    }
}
