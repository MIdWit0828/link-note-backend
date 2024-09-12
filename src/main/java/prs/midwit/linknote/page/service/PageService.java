package prs.midwit.linknote.page.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prs.midwit.linknote.auth.common.exception.NotFoundException;
import prs.midwit.linknote.auth.common.exception.type.ExceptionCode;
import prs.midwit.linknote.page.domain.entity.Page;
import prs.midwit.linknote.page.domain.repo.PageRepository;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;
import prs.midwit.linknote.page.presentation.PageModifyRequest;
import prs.midwit.linknote.project.domain.entitiy.Project;
import prs.midwit.linknote.project.domain.repo.PjtRepository;
import prs.midwit.linknote.project.service.PjtService;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService {
    private final PageRepository pageRepository;
    private final PjtRepository pjtRepository;

    public long save(PageCreateReqest reqest) {
        final Page newPage = Page.from(reqest);

        pjtRepository.findById(reqest.getPjtCode()).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_PROJECT_CODE)
        );

        return pageRepository.save(newPage).getPageCode();
    }

    public void modify(long pageCode, PageModifyRequest request) {
        Page findPage = pageRepository.findById(pageCode).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_PAGE_CODE)
        );
        findPage.modify(request);

    }

    public void delete(long pageCode) {
        Page findPage = pageRepository.findById(pageCode).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_PAGE_CODE)
        );
        pageRepository.delete(findPage);
    }
}
