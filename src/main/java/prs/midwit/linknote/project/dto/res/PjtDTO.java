package prs.midwit.linknote.project.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import prs.midwit.linknote.project.domain.entitiy.Project;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PjtDTO {
    private Long pjtCode;
    private String pjtName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pjtUpdateDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pjtCreateDt;

    public PjtDTO(Long pjtCode, String pjtName, LocalDateTime pjtUpdateDt, LocalDateTime pjtCreateDt) {
        this.pjtCode = pjtCode;
        this.pjtName = pjtName;
        this.pjtUpdateDt = pjtUpdateDt;
        this.pjtCreateDt = pjtCreateDt;
    }

    public static PjtDTO from(Project target) {
        return new PjtDTO(
                target.getMemberCode(),
                target.getPjtName(),
                target.getPjtCreateDt(),
                target.getPjtUpdateDt()
        );
    }
}
