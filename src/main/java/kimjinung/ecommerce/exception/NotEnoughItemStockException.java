package kimjinung.ecommerce.exception;

public class NotEnoughItemStockException extends RuntimeException{
    public NotEnoughItemStockException(String message) {
        super(message);
    }
}
