package ptit.edu.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorMessage> handleAppException(Exception ex) {
        if (ex instanceof AppException) {
            AppException appEx = (AppException) ex;
            return ResponseEntity
                .status(appEx.getStatusCode())
                .body(new ErrorMessage(appEx.getMessage(), appEx.getData()));
        } else 
            ex.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage("có lỗi ở server", null));
        }
}
