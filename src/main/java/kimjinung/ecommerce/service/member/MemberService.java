package kimjinung.ecommerce.service.member;

import kimjinung.ecommerce.dto.member.MemberJoinRequestDto;
import kimjinung.ecommerce.dto.member.MemberJoinResponseDto;

public interface MemberService {
    MemberJoinResponseDto join(MemberJoinRequestDto dto);
}
