package com.example.concert.mappers;


import com.example.concert.dto.response.UserResponseDTO;
import com.example.concert.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserResponseDTO> {

    @Override
    default UserResponseDTO toDto(User entity){

        return UserResponseDTO.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .passwordHash(entity.getPasswordHash())
                .email(entity.getEmail())
                .build();
    }

}

