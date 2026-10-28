package com.example.recruitpro.controller;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    // Biến giả lập để tự tăng ID (thay thế cho Database tạm thời)
    private int currentId = 1;

    @PostMapping
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateCreateDTO candidateDTO) {

        // Đoạn code kiểm tra lỗi (BindingResult) đã được xóa bỏ hoàn toàn.
        // Nếu có lỗi validation, Spring Boot sẽ tự động ném ra Exception
        // và file GlobalExceptionHandler của bạn sẽ tự động bắt lấy nó.

        // Khởi tạo và lưu thông tin ứng viên mới nếu dữ liệu hợp lệ
        Candidate newCandidate = new Candidate();
        newCandidate.setId(currentId++);
        newCandidate.setFullName(candidateDTO.getFullName());
        newCandidate.setEmail(candidateDTO.getEmail());
        newCandidate.setAge(candidateDTO.getAge());
        newCandidate.setYearsOfExperience(candidateDTO.getYearsOfExperience());

        // Trả về mã 201 Created cùng thông tin JSON của ứng viên vừa tạo
        return ResponseEntity.status(HttpStatus.CREATED).body(newCandidate);
    }
}