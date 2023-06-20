package kimjinung.ecommerce.dto.member;

import lombok.Data;

@Data
public class MemberJoinRequestDto {
    private String userId;
    private String password;
    private String email;

}
