package kimjinung.ecommerce.service.category;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.exception.item.CategoryNotFoundException;
import kimjinung.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category register(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category foundCategory = findById(category.getId());
        foundCategory.updateName(category.getName());
        foundCategory.updateParent(category.getParent());
        return foundCategory;
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllByIds(List<Integer> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public void remove(Category category) {
        categoryRepository.delete(category);
    }
}
