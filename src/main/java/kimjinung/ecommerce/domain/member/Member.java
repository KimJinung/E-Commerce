package kimjinung.ecommerce.domain.member;

import kimjinung.ecommerce.domain.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static kimjinung.ecommerce.domain.member.MemberRole.*;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    private String id;

    private String password;

    private String email;

    @Enumerated(STRING)
    private MemberRole role;

    protected Member() {
    }

    public Member(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = USER;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }
}
