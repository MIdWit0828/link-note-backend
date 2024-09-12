package prs.midwit.linknote.page.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageCreateReqest {
    @NotBlank
    private long pjtCode;
    @NotBlank
    private String pageTitle;
}
