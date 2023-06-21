package kimjinung.ecommerce.controller;

import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class BaseApiController {

    protected void validateRequest(BindingResult bindingResult) throws InvalidRequestStateException {
        ArrayList<String> errors = new ArrayList<>();

        if (bindingResult.hasGlobalErrors()) {
            errors.addAll(bindingResult.getGlobalErrors()
                    .stream()
                    .map(error -> makeErrorMessage(error.getObjectName(), error.getDefaultMessage()))
                    .collect(Collectors.toList())
            );
        }
        if (bindingResult.hasFieldErrors()) {
            errors.addAll(bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> makeErrorMessage(error.getObjectName(), error.getDefaultMessage()))
                    .collect(Collectors.toList())
            );
        }

        if (!errors.isEmpty()) throw new InvalidRequestStateException();
    }

    private static String makeErrorMessage(String field, String msg) {
        return String.format("[%s]= %s", field, msg);
    }

}
