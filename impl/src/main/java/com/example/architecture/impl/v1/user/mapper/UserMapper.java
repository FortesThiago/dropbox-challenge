package com.example.architecture.impl.v1.user.mapper;

import com.example.architecture.impl.v1.user.UserModel;
import com.example.architecture.impl.v1.user.repository.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class UserMapper {
    public static UserModel mapToModel(UserEntity userEntity) {
        return UserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .files(Optional.ofNullable(userEntity.getFiles()).orElse(new ArrayList<>()))
                .build();
    }

    public static UserEntity mapToEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .files(userModel.getFiles())
                .build();
    }
}


