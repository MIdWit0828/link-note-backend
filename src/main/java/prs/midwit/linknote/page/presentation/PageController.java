package prs.midwit.linknote.page.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prs.midwit.linknote.page.service.PageService;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PageController {
    private  final PageService pageService;


}
