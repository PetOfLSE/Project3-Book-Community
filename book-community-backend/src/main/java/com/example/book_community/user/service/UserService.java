package com.example.book_community.user.service;

import com.example.book_community.common.converter.Converter;
import com.example.book_community.user.dto.UserDto;
import com.example.book_community.user.entity.UserEntity;
import com.example.book_community.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Converter converter;

    public UserEntity register(@Valid UserDto dto) {
        UserEntity user = converter.toEntity(dto);
        UserEntity save = userRepository.save(user);
        return save;
    }

    public boolean login(@Valid UserDto dto) {
        UserEntity user = converter.toEntity(dto);
        Optional<UserEntity> byEmail = userRepository.findByEmail(user.getEmail());
        if(byEmail.isPresent()) {
            user = byEmail.get();
            if(user.getPassword().equals(dto.getPassword())) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
