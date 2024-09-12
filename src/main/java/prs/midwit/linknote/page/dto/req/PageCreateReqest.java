package prs.midwit.linknote.page.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageCreateReqest {
    @NotNull
    private Long pjtCode;
    @NotBlank
    private String pageTitle;
}
