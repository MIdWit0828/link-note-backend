package prs.midwit.linknote.page.dto.res;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import prs.midwit.linknote.page.domain.entity.Page;
import prs.midwit.linknote.page.dto.SimplePageDTO;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PagesResponse {
    List<SimplePageDTO> pages;

    public static PagesResponse of(List<Page> pages) {
        return new PagesResponse(
                pages.stream().map(SimplePageDTO::of).toList()
        );
    }
}
