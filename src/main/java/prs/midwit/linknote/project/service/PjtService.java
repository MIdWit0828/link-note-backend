package prs.midwit.linknote.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prs.midwit.linknote.auth.common.exception.NoContentsException;
import prs.midwit.linknote.auth.common.exception.type.ExceptionCode;
import prs.midwit.linknote.project.domain.entitiy.Project;
import prs.midwit.linknote.project.domain.repo.PjtRepository;
import prs.midwit.linknote.project.dto.res.PjtsResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PjtService {
    private final PjtRepository pjtRepository;


    @Transactional(readOnly = true)
    public PjtsResponse getPjtsByMemberCode(Long memberCode) {
        List<Project> projects = pjtRepository.findByMemberCode(memberCode);
        if (projects.isEmpty()) {
            //작성한 프로젝트가 없어 조회할수 없는 경우
            throw new NoContentsException(ExceptionCode.NO_CONTENTS_PROJECT);
        }
        return PjtsResponse.from(projects);
    }
}
