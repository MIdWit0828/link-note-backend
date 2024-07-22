package prs.midwit.linknote.member.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import prs.midwit.linknote.member.domain.entity.Member;

@Getter
@RequiredArgsConstructor
public class ProfileResponse {

    private final String memberId;
    private final String memberName;
    private final String memberEmail;


    public static ProfileResponse from(Member member) {

        return new ProfileResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberEmail()
        );
    }
}
