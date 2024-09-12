package prs.midwit.linknote.page.dto;

import lombok.*;
import prs.midwit.linknote.page.domain.entity.Page;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimplePageDTO {
    private long pageCode;
    private String pageTitle;

    public static SimplePageDTO of(Page page) {
        return new SimplePageDTO(page.getPageCode(), page.getPageTitle());
    }
}
