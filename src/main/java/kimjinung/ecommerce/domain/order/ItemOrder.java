package kimjinung.ecommerce.domain.order;

import kimjinung.ecommerce.domain.common.BaseEntity;
import kimjinung.ecommerce.domain.item.Item;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class ItemOrder extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer count;

    public ItemOrder() {
    }

    public ItemOrder(Order order, Item item, Integer count) {
        this.item = item;
        this.order = order;
        this.count = count;
    }
}
