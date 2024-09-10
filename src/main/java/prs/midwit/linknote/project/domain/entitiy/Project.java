package prs.midwit.linknote.project.domain.entitiy;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_project")
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pjtCode;
    private String pjtName;
    private LocalDateTime pjtCreateDt;
    private LocalDateTime pjtUpdateDt;
    private boolean isDeleted;
    private LocalDateTime pjtDeleteDt;
    private long memberCode;
}
