package kimjinung.ecommerce.domain.item;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class CategoryItem {

    @Id
    @Column(name = "category_item_id")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected CategoryItem() {
    }

    public CategoryItem(Item item, Category category) {
        this.item = item;
        this.category = category;
    }
}

