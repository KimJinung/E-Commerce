package kimjinung.ecommerce.exception.category;

public class AlreadyExistCategoryNameException extends RuntimeException{

    public AlreadyExistCategoryNameException(String message) {
        super(message);
    }
}
