package prs.midwit.linknote.page.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import prs.midwit.linknote.member.service.MemberService;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;
import prs.midwit.linknote.page.service.PageService;

import java.net.URI;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);
    private  final PageService pageService;
    private final MemberService memberService;

    //새로운 패이지 생성
    @PostMapping("/pages")
    public ResponseEntity<Void> savePage(
            @RequestBody @Valid PageCreateReqest reqest
    ) {
        log.info("+++++++++++++++++pjtCode{}+++++++++++++",reqest.getPjtCode());
        final long pageCode = pageService.save(reqest);

        return ResponseEntity.created(URI.create("/api/v1/pages/" + pageCode)).build();
    }
}
