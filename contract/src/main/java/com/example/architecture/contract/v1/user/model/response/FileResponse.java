package com.example.architecture.contract.v1.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {
    private String id;
    private String originalName;
    private String ftpName;
    private String idOwner;
}
