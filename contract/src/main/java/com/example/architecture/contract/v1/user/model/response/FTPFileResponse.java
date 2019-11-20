package com.example.architecture.contract.v1.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FTPFileResponse {
    private String name;
    private long size;
}
