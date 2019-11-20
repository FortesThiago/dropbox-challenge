package com.example.architecture.contract.v1.user.model.response;

import com.example.architecture.impl.v1.file.model.FileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private List<FileModel> files;
}
