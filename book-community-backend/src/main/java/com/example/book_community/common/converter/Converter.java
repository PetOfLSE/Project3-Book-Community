package com.example.book_community.common.converter;

import com.example.book_community.user.dto.UserDto;
import com.example.book_community.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Converter {

    public UserEntity toEntity(UserDto dto){
        return UserEntity.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles("USER")
                .build();
    }
}
