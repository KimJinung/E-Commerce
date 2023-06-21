package kimjinung.ecommerce.exception.item;

public class NotEnoughItemStockException extends RuntimeException{
    public NotEnoughItemStockException(String message) {
        super(message);
    }
}
