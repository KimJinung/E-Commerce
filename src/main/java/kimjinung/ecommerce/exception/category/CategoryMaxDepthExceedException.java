package kimjinung.ecommerce.exception.category;

public class CategoryMaxDepthExceedException extends RuntimeException{

    public CategoryMaxDepthExceedException(String message) {
        super(message);
    }
}
