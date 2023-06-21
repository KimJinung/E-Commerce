package kimjinung.ecommerce.service.category;

import kimjinung.ecommerce.domain.item.Category;

import java.util.List;

public interface CategoryService {

    Category register(String name, int parentId);
    Category update(int id, String name, int parentId);
    Category findById(int id);
    List<Category> findAllByIds(List<Integer> ids);
    void remove(Category category);
}
