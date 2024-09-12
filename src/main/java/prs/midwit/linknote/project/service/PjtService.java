package prs.midwit.linknote.project.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prs.midwit.linknote.auth.common.exception.BadRequestException;
import prs.midwit.linknote.auth.common.exception.NoContentsException;
import prs.midwit.linknote.auth.common.exception.NotFoundException;
import prs.midwit.linknote.auth.common.exception.type.ExceptionCode;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;
import prs.midwit.linknote.page.service.PageService;
import prs.midwit.linknote.project.domain.entitiy.Project;
import prs.midwit.linknote.project.domain.repo.PjtRepository;
import prs.midwit.linknote.project.dto.req.PjtModifyRequest;
import prs.midwit.linknote.project.dto.res.PjtsResponse;

import java.util.List;

import static prs.midwit.linknote.auth.common.exception.type.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PjtService {

    private static final Logger log = LoggerFactory.getLogger(PjtService.class);
    private final PjtRepository pjtRepository;
    private final PageService pageService;


    // 자신이 작성한 프로젝트 목록 조회
    @Transactional(readOnly = true)
    public PjtsResponse getPjtsByMemberCode(Long memberCode) {
        List<Project> projects = pjtRepository.findByMemberCode(memberCode);
        if (projects.isEmpty()) {
            //작성한 프로젝트가 없어 조회할수 없는 경우
            throw new NoContentsException(ExceptionCode.NO_CONTENTS_PROJECT);
        }
        return PjtsResponse.from(projects);
    }

    //새로운 프로젝트 작성
    public long save(String pjtName, Long memberCode) {
        Project newProject = Project.saveOf(pjtName, memberCode);
        //프로젝트가 생성되면 새로운 패이지를 1개 같이 생성
        pageService.save(new PageCreateReqest(newProject.getPjtCode(), "새문서"));
        return pjtRepository.save(newProject).getPjtCode();
    }

    public void modify(Long memberCode, long pjtCode, PjtModifyRequest request) {

        Project findProject = pjtRepository.findById(pjtCode).orElseThrow(
                () -> new NotFoundException(NOT_FOUND_PROJECT_CODE)
        );
        if (request.getPjtName() == null) {
            throw new BadRequestException(BAD_REQUEST_PROJECT_CONTENTS);
        }
        if (findProject.getMemberCode() != memberCode) {
            throw new BadRequestException(BAD_REQUEST_PROJECT_MEMBER);
        }
        findProject.modity(request);


    }

    public void delete(Long memberCode, long pjtCode) {
        Project findProject = pjtRepository.findById(pjtCode).orElseThrow(
                () -> new NotFoundException(NOT_FOUND_PROJECT_CODE)
        );
        log.info("내용 {}",findProject.isDeleted());
        if (findProject.getMemberCode() != memberCode) {
            throw new BadRequestException(BAD_REQUEST_PROJECT_MEMBER);
        }
        if (findProject.isDeleted()) {
            throw new BadRequestException(BAD_REQUEST_PROJECT_ALREADY_DELETED);
        }
        pjtRepository.delete(findProject);
    }
}
