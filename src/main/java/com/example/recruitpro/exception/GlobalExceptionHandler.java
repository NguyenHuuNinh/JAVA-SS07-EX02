package com.example.recruitpro.exception;

import com.example.recruitpro.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Tạo Map để chứa danh sách lỗi (tên trường - thông báo lỗi)
        Map<String, String> errors = new HashMap<>();

        // Trích xuất lỗi từ BindingResult và đưa vào Map
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Đóng gói vào ApiResponse theo đúng format kết quả mong muốn
        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                "Dữ liệu không hợp lệ !",
                errors,
                "400 BAD_REQUEST"
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}