package prs.midwit.linknote.project.domain.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_project")
@Getter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pjtCode;
    private String pjtName;
    @CreatedDate
    private LocalDateTime pjtCreateDt;
    @LastModifiedBy
    private LocalDateTime pjtUpdateDt;
    private boolean isDeleted;
    private LocalDateTime pjtDeleteDt;
    private long memberCode;

    public Project(String pjtName, long memberCode) {
        this.pjtName = pjtName;
        this.memberCode = memberCode;
    }

    public static Project saveOf(String pjtName, long memberCode) {
        return new Project(
                pjtName,
                memberCode
        );
    }
}
