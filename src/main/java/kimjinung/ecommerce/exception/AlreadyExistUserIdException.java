package kimjinung.ecommerce.exception;

public class AlreadyExistUserIdException extends RuntimeException{

    public AlreadyExistUserIdException(String message) {
        super(message);
    }
}
