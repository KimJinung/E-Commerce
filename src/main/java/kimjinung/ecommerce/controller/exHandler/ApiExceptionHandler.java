package kimjinung.ecommerce.controller.exHandler;

import kimjinung.ecommerce.dto.ResponseDto;
import kimjinung.ecommerce.dto.error.Error;
import kimjinung.ecommerce.dto.error.ErrorResult;
import kimjinung.ecommerce.exception.category.AlreadyExistCategoryNameException;
import kimjinung.ecommerce.exception.category.CategoryMaxDepthExceedException;
import kimjinung.ecommerce.exception.category.CategoryNotFoundException;
import kimjinung.ecommerce.exception.item.ItemNotFoundException;
import kimjinung.ecommerce.exception.item.NotEnoughItemStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(RuntimeException ex) {
        return baseException(500, Error.SERVER_ERROR, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughItemStockException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(NotEnoughItemStockException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(ItemNotFoundException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(CategoryNotFoundException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistCategoryNameException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(AlreadyExistCategoryNameException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryMaxDepthExceedException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(CategoryMaxDepthExceedException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    private ResponseDto<ErrorResult> baseException(int statusCode, Error error, RuntimeException exception) {
        log.info(exception.getMessage());
        ErrorResult errorResult = new ErrorResult(error.toString(), exception.getMessage());
        return new ResponseDto<>(statusCode, errorResult);
    }
}
