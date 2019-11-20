package com.example.architecture.impl.v1.user;

import com.example.architecture.impl.v1.file.model.FileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class UserModel {
    private String id;
    private String name;
    private String email;
    private List<FileModel> files;
}
