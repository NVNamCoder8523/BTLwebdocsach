package ptit.edu.vn.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AppException extends RuntimeException {
    private HttpStatus statusCode;
    private Object data;
    private String solution;

    public AppException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public AppException(HttpStatus statusCode, String message, String solution) {
        this(statusCode, message);
        this.solution = solution;
    }

    public AppException(HttpStatus statusCode, String message, Object data) {
        this(statusCode, message);
        this.data = data;
    }   
}
