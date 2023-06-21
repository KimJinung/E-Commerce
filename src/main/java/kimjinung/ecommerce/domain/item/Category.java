package kimjinung.ecommerce.domain.item;


import kimjinung.ecommerce.domain.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "category_id")
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = ALL)
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

    public void updateName(String name) {
        this.name = name;
    }

    public void updateParent(Category parent) {
        this.parent = parent;
    }
}
