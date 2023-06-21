package kimjinung.ecommerce.domain.order;

import kimjinung.ecommerce.domain.common.BaseEntity;
import kimjinung.ecommerce.domain.shipment.Shipment;
import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.domain.member.Member;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static kimjinung.ecommerce.domain.order.OrderStatus.*;

@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "item", cascade = ALL)
    private final List<ItemOrder> items = new ArrayList<>();

    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order" , cascade = ALL)
    private final List<Shipment> shipment = new ArrayList<>();

    protected Order() {
    }

    public Order(Member member, Address address) {
        this.member = member;
        this.totalPrice = 0L;
        this.status = PROCESSING;
        this.shipment.add(new Shipment(this, address));
    }

    public void addItem(Item item, int count) {
        item.reduceStock(count);
        totalPrice += item.calculatePriceByCount(count);
        this.items.add(new ItemOrder(this, item, count));
    }

    public void done() {
        this.status = COMPLETE;
    }

    public void cancel() {
        this.status = CANCEL;
    }

    public void refund() {
        this.status = REFUND;
    }

    public void exchange() {
        this.status = EXCHANGE;
    }

}

