package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.request.UserRequest;
import com.example.architecture.contract.v1.user.model.response.UserResponse;
import com.example.architecture.impl.v1.user.UserModel;

public class UserMapper {

    public static UserResponse mapToContract(UserModel userModel) {
        return UserResponse.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .files(userModel.getFiles())
                .build();
    }

    public static UserModel mapToImpl(UserRequest userRequest) {
        return UserModel.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();
    }
}
