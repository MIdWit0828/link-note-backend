package prs.midwit.linknote.project.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import prs.midwit.linknote.member.service.MemberService;

import prs.midwit.linknote.page.dto.res.PagesResponse;
import prs.midwit.linknote.page.service.PageService;
import prs.midwit.linknote.project.dto.req.PjtCreateRequest;
import prs.midwit.linknote.project.dto.req.PjtModifyRequest;
import prs.midwit.linknote.project.dto.res.PjtsResponse;
import prs.midwit.linknote.project.service.PjtService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PjtController {

    private final MemberService memberService;
    private final PjtService pjtService;
    private final PageService pageService;

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


    // 새로운 프로젝트 작성
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

    //기존의 프로젝트 수정
    @PutMapping("/pjts/{pjtCode}")
    public ResponseEntity<Void> modifyProject(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable long pjtCode,
            @RequestBody PjtModifyRequest request
    ) {
        String userId = userDetails.getUsername();
        Long memberCode = memberService.getMemberCodeByName(userId);

        pjtService.modify(memberCode, pjtCode, request);

        return ResponseEntity.ok().build();
    }

    //프로젝트 삭제
    @DeleteMapping("/pjts/{pjtCode}")
    public ResponseEntity<Void> deleteProject(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable long pjtCode
    ) {
        String userId = userDetails.getUsername();
        Long memberCode = memberService.getMemberCodeByName(userId);

        pjtService.delete(memberCode, pjtCode);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pjts/pages/{pjtCode}")
    public ResponseEntity<PagesResponse> findPagesByPjtCode(
            @PathVariable long pjtCode
    ) {
        PagesResponse res = pageService.findPagesByPjtCode(pjtCode);

        return ResponseEntity.ok(res);
    }
}
