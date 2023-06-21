package kimjinung.ecommerce.domain.item;

import kimjinung.ecommerce.domain.common.BaseEntity;
import kimjinung.ecommerce.domain.member.Member;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "review_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String text;

    protected Review() {
    }

    public Review(Member member, Item item, String text) {
        this.member = member;
        this.item = item;
        this.text = text;
    }

    public void updateReview(String text) {
        this.text = text;
    }
}
