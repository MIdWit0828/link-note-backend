package prs.midwit.linknote.page.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import prs.midwit.linknote.page.domain.entity.Page;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page,Long> {

    Optional<Page> findByPageCodeAndIsDeletedFalse(long pageCode);
}
