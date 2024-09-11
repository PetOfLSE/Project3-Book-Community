package com.example.book_community.user.controller;

import com.example.book_community.user.dto.UserDto;
import com.example.book_community.user.entity.UserEntity;
import com.example.book_community.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserEntity register(@Valid @RequestBody UserDto dto) {
        UserEntity register = userService.register(dto);
        return register;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDto dto) {
        Boolean bool = userService.login(dto);
        if(bool){
            return ResponseEntity.status(HttpStatus.OK).body("로그인의 성공하셨습니다. ");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 또는 비밀번호가 틀렸습니다.");
        }
    }
}
