package prs.midwit.linknote.page.dto.res;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import prs.midwit.linknote.page.domain.entity.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse {
    private long pageCode;
    private String pageTitle;
    private String pageText;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime pageCreateDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime pageUpdateDt;
    private long categoryCode;

    public static PageResponse from(Page findPage) {
        return new PageResponse(
                findPage.getPageCode(),
                findPage.getPageTitle(),
                findPage.getPageText(),
                findPage.getPageCreateDt(),
                findPage.getPageUpdateDt(),
                findPage.getCategoryCode()
        );
    }

}
