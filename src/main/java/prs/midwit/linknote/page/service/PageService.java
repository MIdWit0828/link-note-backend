package prs.midwit.linknote.page.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prs.midwit.linknote.page.domain.entity.Page;
import prs.midwit.linknote.page.domain.repo.PageRepository;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService {
    private final PageRepository pageRepository;


    public long save(PageCreateReqest reqest) {
        final Page newPage = Page.from(reqest);

        return pageRepository.save(newPage).getPageCode();
    }

}
