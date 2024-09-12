package prs.midwit.linknote.project.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import prs.midwit.linknote.project.domain.entitiy.Project;

import java.util.List;
import java.util.Optional;

public interface PjtRepository extends JpaRepository<Project, Long> {

    List<Project> findByMemberCode(Long memberCode);
}
