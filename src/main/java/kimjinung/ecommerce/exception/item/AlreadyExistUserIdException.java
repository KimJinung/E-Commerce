package kimjinung.ecommerce.exception.item;

public class AlreadyExistUserIdException extends RuntimeException{

    public AlreadyExistUserIdException(String message) {
        super(message);
    }
}
