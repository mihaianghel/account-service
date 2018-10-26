package digital.and.accountservice.exception.handler;

import digital.and.accountservice.exception.GenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collection;
import java.util.stream.Collectors;

@ControllerAdvice
public class HttpExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpExceptionHandler.class);

    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleGenericException(GenericException ex) {

        LOGGER.error("Generic exception encountered: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpExceptionResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        LOGGER.error("Method argument not valid exception encountered: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpExceptionResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(getValidationErrors(ex.getBindingResult()))
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        LOGGER.error("Method argument type mismatch exception encountered: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpExceptionResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleGenericException(Exception ex) {

        LOGGER.error("Generic exception encountered", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                HttpExceptionResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build());
    }

    private Collection<String> getValidationErrors(BindingResult result) {
        return result.getAllErrors().stream()
                .map(e -> String.format("Parameter %s %s", ((FieldError) e).getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
