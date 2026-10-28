package com.example.recruitpro.controller;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    // Biến giả lập để tự tăng ID (thay thế cho Database tạm thời)
    private int currentId = 1;

    @PostMapping
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateCreateDTO candidateDTO, BindingResult bindingResult) {

        // Trả về lỗi 400 nếu dữ liệu vi phạm các quy tắc trong DTO
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ liệu không hợp lệ : ");
        }

        // Tạo ứng viên mới nếu dữ liệu chuẩn
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