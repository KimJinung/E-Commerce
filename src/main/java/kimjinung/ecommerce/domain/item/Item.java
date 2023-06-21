package kimjinung.ecommerce.domain.item;


import kimjinung.ecommerce.domain.common.BaseEntity;
import kimjinung.ecommerce.exception.item.NotEnoughItemStockException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private Integer discountRate;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<CategoryItem> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private final List<Review> reviews = new ArrayList<>();

    protected Item() {
    }

    public Item(String name,
                Integer price,
                Integer stockQuantity,
                Integer discountRate) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.discountRate = discountRate;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrice(Integer price) {
        this.price = price;
    }

    public void updateDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public void updateStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int count) {
        this.stockQuantity += count;
    }

    public void reduceStock(int count) {
        int restStock = this.stockQuantity - count;

        if (restStock < 0) {
            String msg = String.format("%s not have enough stock quantity", this.name);
            throw new NotEnoughItemStockException(msg);
        }

        this.stockQuantity = restStock;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addCategory(Category category) {
        this.categories.add(new CategoryItem(this, category));
        category.addItem(this);
    }

    public void addCategory(List<Category> categories) {
        categories.forEach(c -> {
            this.categories.add(new CategoryItem(this, c));
            c.addItem(this);
        });
    }

    public void removeAllCategory() {
        this.categories.clear();
    }

    public Long calculatePriceByCount(int count) {
        if (this.discountRate == 0) {
            return (long) this.price * count;
        }
        int discountedPrice = this.price - (this.price * this.discountRate / 100);
        return (long) discountedPrice * count;
    }

}
