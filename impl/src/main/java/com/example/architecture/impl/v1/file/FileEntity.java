package com.example.architecture.impl.v1.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileEntity {
    private String id;
    private String idOwner;
    private String originalName;
    private String ownerId;
    private String ftpName;
}
