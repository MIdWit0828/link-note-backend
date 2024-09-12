package prs.midwit.linknote.page.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import prs.midwit.linknote.page.dto.req.PageCreateReqest;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_page")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tbl_page SET is_deleted = 1, page_delete_dt = CURRENT_TIMESTAMP WHERE page_code = ?")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageCode;
    private String pageTitle;
    private String pageText;
    @CreatedDate
    private LocalDateTime pageCreateDt;
    @LastModifiedDate
    private LocalDateTime pageUpdateDt;
    private boolean isDeleted;
    private LocalDateTime pageDeleteDt;
    private Long pjtCode;
    private Long categoryCode;

    public Page(String pageTitle, long pjtCode) {
        this.pageTitle = pageTitle;
        this.pjtCode = pjtCode;
    }

    public static Page from(PageCreateReqest reqest) {
        return new Page(
                reqest.getPageTitle(),
                reqest.getPjtCode()
        );
    }
}
