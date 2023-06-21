package kimjinung.ecommerce.exception.member;

public class AlreadyExistUserIdException extends RuntimeException{

    public AlreadyExistUserIdException(String message) {
        super(message);
    }
}
