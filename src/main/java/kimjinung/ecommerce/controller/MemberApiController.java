package kimjinung.ecommerce.controller;

import kimjinung.ecommerce.dto.BaseResponseDto;
import kimjinung.ecommerce.dto.member.MemberJoinRequestDto;
import kimjinung.ecommerce.dto.member.MemberJoinResponseDto;
import kimjinung.ecommerce.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    @PostMapping
    public BaseResponseDto<MemberJoinResponseDto> join(
            @RequestBody @Validated MemberJoinRequestDto dto,
            BindingResult bindingResult
    ) {
        //validateRequest(bindingResult);
        MemberJoinResponseDto response = memberService.join(dto);
        return new BaseResponseDto<>(200, response);
    }
}
