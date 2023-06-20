package kimjinung.ecommerce.domain.shipment;


import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipCode;
}
