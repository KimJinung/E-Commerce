package kimjinung.ecommerce.dto.member;

import lombok.Data;

@Data
public class MemberJoinResponseDto {

    private String userId;
    private String email;

    protected MemberJoinResponseDto() {
    }

    public MemberJoinResponseDto(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
