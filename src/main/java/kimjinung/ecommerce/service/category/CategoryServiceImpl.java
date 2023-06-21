package kimjinung.ecommerce.service.category;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.exception.category.AlreadyExistCategoryNameException;
import kimjinung.ecommerce.exception.category.CategoryMaxDepthExceedException;
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
    public Category register(String name, int parentId) {
        categoryRepository.findByName(name).ifPresent(category -> {
                    throw new AlreadyExistCategoryNameException("이미 존재하는 카테고리 이름입니다.");
                }
        );
        Category parent = findById(parentId);
        if (parent != null && parent.getParent() != null) {
            throw new CategoryMaxDepthExceedException("카테고리는 2단계를 넘어설 수 없습니다.");
        }
        Category category = new Category(parent, name);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(int id, String name, int parentId) {
        Category foundCategory = findById(id);
        foundCategory.updateName(name);
        Category parent = findById(parentId);
        foundCategory.updateParent(parent);
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
