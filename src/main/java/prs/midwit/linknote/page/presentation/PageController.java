package prs.midwit.linknote.page.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prs.midwit.linknote.member.service.MemberService;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;
import prs.midwit.linknote.page.dto.res.PageResponse;
import prs.midwit.linknote.page.service.PageService;

import java.net.URI;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);
    private final PageService pageService;
    private final MemberService memberService;

    //새로운 패이지 생성
    @PostMapping("/pages")
    public ResponseEntity<Void> savePage(
            @RequestBody @Valid PageCreateReqest reqest
    ) {
        final long pageCode = pageService.save(reqest);

        return ResponseEntity.created(URI.create("/api/v1/pages/" + pageCode)).build();
    }

    //패이지 수정
    @PutMapping("/pages/{pageCode}")
    public ResponseEntity<Void> modifyPage(
            @PathVariable long pageCode,
            @RequestBody PageModifyRequest request
    ) {
        pageService.modify(pageCode, request);

        return ResponseEntity.ok().build();
    }

    //패이지 삭제
    @DeleteMapping("/pages/{pageCode}")
    public ResponseEntity<Void> deletePage(
            @PathVariable long pageCode
    ) {
        pageService.delete(pageCode);

        return ResponseEntity.noContent().build();
    }

    //특정 페이지 조회
    @GetMapping("/pages/{pageCode}")
    public ResponseEntity<PageResponse> findPage(
            @PathVariable long pageCode
    ) {
        PageResponse response = pageService.findByPageCode(pageCode);

        return ResponseEntity.ok(response);
    }
}
