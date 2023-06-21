package kimjinung.ecommerce.exception.item;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException() {
        super();
    }
}
