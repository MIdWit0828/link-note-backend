package prs.midwit.linknote.page.presentation;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageModifyRequest {
    private String pageTitle;
    private String pageText;
}
