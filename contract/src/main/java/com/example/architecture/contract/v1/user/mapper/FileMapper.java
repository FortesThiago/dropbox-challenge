package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.response.FTPFileResponse;
import com.example.architecture.contract.v1.user.model.response.FileResponse;
import com.example.architecture.impl.v1.file.model.FTPFile;
import com.example.architecture.impl.v1.file.model.FileModel;

public class FileMapper {
    public static FileResponse mapToContract(FileModel model) {
        return FileResponse.builder()
                .id(model.getId())
                .idOwner(model.getIdOwner())
                .originalName(model.getOriginalName())
                .ftpName(model.getFtpName())
                .build();
    }

    public static FTPFileResponse mapToFTPFileResponse(FTPFile ftpFile) {
        return FTPFileResponse.builder()
                .name(ftpFile.getName())
                .size(ftpFile.getSize())
                .build();
    }
}
