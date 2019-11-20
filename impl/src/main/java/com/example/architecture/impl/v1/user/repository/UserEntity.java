package com.example.architecture.impl.v1.user.repository;

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
public class UserEntity {
    private String id;
    private String name;
    private String email;
    private List<FileModel> files;
}
