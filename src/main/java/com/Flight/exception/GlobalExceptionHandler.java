//package com.Flight.exception;
//
//import jakarta.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import com.Flight.exception.DailyFlightLimitExceededException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        log.error("Resource not found: {}", ex.getMessage());
//        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
//    }
//
//    @ExceptionHandler(DuplicateResourceException.class)
//    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
//        log.error("Duplicate resource: {}", ex.getMessage());
//        return createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
//    }
//
//    @ExceptionHandler(DailyFlightLimitExceededException.class)
//    public ResponseEntity<ErrorResponse> handleDailyFlightLimitExceededException(DailyFlightLimitExceededException ex) {
//        log.error("Daily flight limit exceeded: {}", ex.getMessage());
//        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//            errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getConstraintViolations().forEach(violation ->
//            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
//        );
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message) {
//        ErrorResponse errorResponse = new ErrorResponse(
//            status.value(),
//            status.getReasonPhrase(),
//            message,
//            LocalDateTime.now()
//        );
//        return new ResponseEntity<>(errorResponse, status);
//    }
//}
