package kimjinung.ecommerce.domain.shipment;

import kimjinung.ecommerce.domain.order.Address;
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

    private String city;
    private String street;
    private String zipCode;

    protected Shipment() {
    }

    public Shipment(Order order, Address address) {
        this.order = order;
        this.city = address.getCity();
        this.street = address.getStreet();
        this.zipCode = address.getZipCode();
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
