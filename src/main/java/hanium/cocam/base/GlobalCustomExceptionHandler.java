package hanium.cocam.base;

import hanium.cocam.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

//@ControllerAdvice
public class GlobalCustomExceptionHandler extends ResponseEntityExceptionHandler {
    // 접근 거부 예외
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseDTO.builder()
                        .result(false)
                        .status(HttpStatus.FORBIDDEN.value())
                        .message(ex.getMessage())
                        .build());
    }

    // JWT 만료 예외
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseDTO.builder()
                        .result(false)
                        .status(HttpStatus.FORBIDDEN.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseDTO.builder()
                        .result(false)
                        .status(HttpStatus.FORBIDDEN.value())
                        .message("Invalid JWT signature: " + ex.getMessage())
                        .build());
    }

    // 데이터베이스 연결 예외 등
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .result(false)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Database error: " + ex.getMessage())
                        .build());
    }

    // 그 외 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .result(false)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("An error occurred: " + ex.getMessage())
                        .build());
    }
}
