package prs.midwit.linknote.page.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import prs.midwit.linknote.page.domain.entity.Page;

public interface PageRepository extends JpaRepository<Page,Long> {
}
