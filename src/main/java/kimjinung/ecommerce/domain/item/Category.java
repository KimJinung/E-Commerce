package kimjinung.ecommerce.domain.item;


import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "category_id")
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private final List<Category> child = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private final List<CategoryItem> items = new ArrayList<>();

    protected Category() {
    }

    public Category(Category parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public void addItem(Item item) {
        this.items.add(new CategoryItem(item, this));
    }
}
