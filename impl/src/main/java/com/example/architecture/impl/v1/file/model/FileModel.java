package com.example.architecture.impl.v1.file.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileModel {
    private String id;
    private String idOwner;
    private String originalName;
    private String ftpName;

}
