package kimjinung.ecommerce.service.member;

import kimjinung.ecommerce.domain.member.Member;
import kimjinung.ecommerce.dto.member.MemberJoinRequestDto;
import kimjinung.ecommerce.dto.member.MemberJoinResponseDto;
import kimjinung.ecommerce.exception.AlreadyExistUserIdException;
import kimjinung.ecommerce.exception.MemberNotFoundException;
import kimjinung.ecommerce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberJoinResponseDto join(MemberJoinRequestDto dto) {
        String userId = dto.getUserId();

        memberRepository.findById(userId).ifPresent(id -> {
            String msg = String.format("%s is already used id", id);
            throw new AlreadyExistUserIdException(msg);
        });

        String password = dto.getPassword();
        String email = dto.getEmail();

        Member member = new Member(userId, password, email);
        memberRepository.save(member);

        Member result = memberRepository.findById(userId).orElseThrow(() -> {
            throw new MemberNotFoundException("Register fail");
        });

        return new MemberJoinResponseDto(
                result.getId(),
                result.getEmail()
        );
    }

}
