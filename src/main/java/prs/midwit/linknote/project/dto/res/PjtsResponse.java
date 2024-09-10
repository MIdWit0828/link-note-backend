package prs.midwit.linknote.project.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import prs.midwit.linknote.project.domain.entitiy.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PjtsResponse {
    List<PjtDTO> pjts;

    public PjtsResponse(List<PjtDTO> pjts) {
        this.pjts = pjts;
    }

    public static PjtsResponse from(List<Project> projects) {
        List<PjtDTO> pjts = projects.stream()
                                    .map(PjtDTO::from)
                                    .collect(Collectors.toList());

        return new PjtsResponse(pjts);
    }
}
