package prs.midwit.linknote.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prs.midwit.linknote.auth.common.exception.NotFoundException;
import prs.midwit.linknote.auth.dto.LoginDto;
import prs.midwit.linknote.member.domain.entity.Member;
import prs.midwit.linknote.member.domain.repo.MemberRepository;
import prs.midwit.linknote.member.dto.req.MemberSignupRequest;
import prs.midwit.linknote.member.dto.res.ProfileResponse;

import static prs.midwit.linknote.auth.common.exception.type.ExceptionCode.NOT_FOUND_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(final MemberSignupRequest memberRequest) {

        final Member newMember = Member.of(
                memberRequest.getMemberId(),
                passwordEncoder.encode(memberRequest.getMemberPassword()),
                memberRequest.getMemberName(),
                memberRequest.getMemberEmail()
        );

        memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public LoginDto findByMemberId(String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                                        .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return LoginDto.from(member);
    }

    public void updateRefreshToken(String memberId, String refreshToken) {

        Member member = memberRepository.findByMemberId(memberId)
                                        .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        member.updateRefreshToken(refreshToken);

    }

    @Transactional(readOnly = true)
    public LoginDto findByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                                        .orElseThrow(() -> new NotFoundException(NOT_FOUND_REFRESH_TOKEN));

        return LoginDto.from(member);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                                        .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return ProfileResponse.from(member);

    }

    public Long getMemberCodeByName(String userId) {
        Member member = memberRepository.findByMemberId(userId)
                                        .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        return member.getMemberCode();
    }
}
