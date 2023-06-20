package kimjinung.ecommerce.domain.shipment;

import kimjinung.ecommerce.domain.order.Order;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;
import static kimjinung.ecommerce.domain.shipment.ShipmentStatus.*;

@Getter
@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "sipment_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Embedded
    private Address address;

    protected Shipment() {
    }

    public Shipment(Order order, Address address) {
        this.order = order;
        this.address = address;
        this.status = PENDING;
    }

    public void startShip() {
        this.status = SHIP;
    }

    public void cancel() {
        this.status = CANCEL;
    }

    public void done() {
        this.status = COMPLETE;
    }
}
