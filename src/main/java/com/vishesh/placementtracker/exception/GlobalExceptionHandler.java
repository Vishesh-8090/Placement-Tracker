package com.vishesh.placementtracker.exception;

import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.util.ApiResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponseBuilder.error(ex.getMessage()));
    }

    @ExceptionHandler(CompanyAlreadyExistsExecption.class)
    public ResponseEntity<ApiResponse<Void>> handleCompanyAlreadyExistsException(CompanyAlreadyExistsExecption ex){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponseBuilder.error(ex.getMessage()));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCompanyNotFoundException(CompanyNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseBuilder.error(ex.getMessage()));
    }

    @ExceptionHandler(InvalidCompanyDataException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCompanyDataException(InvalidCompanyDataException ex){

        return ResponseEntity.badRequest().body(ApiResponseBuilder.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseBuilder.error(
                        "Validation failed",
                        errors
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseBuilder.error("An unexpected error occurred."));
    }
}
