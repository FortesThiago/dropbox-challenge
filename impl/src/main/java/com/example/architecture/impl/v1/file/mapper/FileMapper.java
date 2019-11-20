package com.example.architecture.impl.v1.file.mapper;

import com.example.architecture.impl.v1.file.FileEntity;
import com.example.architecture.impl.v1.file.model.FTPFile;
import com.example.architecture.impl.v1.file.model.FileModel;

public class FileMapper {
    public static FileEntity mapToEntity(FileModel fileModel) {
        return FileEntity.builder()
                .id(fileModel.getId())
                .idOwner(fileModel.getIdOwner())
                .ftpName(fileModel.getFtpName())
                .originalName(fileModel.getOriginalName())
                .build();
    }

    public static FileModel mapToFileModel(FileEntity fileEntity) {
        return FileModel.builder()
                .id(fileEntity.getId())
                .idOwner(fileEntity.getIdOwner())
                .originalName(fileEntity.getOriginalName())
                .ftpName(fileEntity.getFtpName())
                .build();
    }

    public static FTPFile mapToFTPFile(org.apache.commons.net.ftp.FTPFile ftpFile) {
        return FTPFile.builder()
                .name(ftpFile.getName())
                .size(ftpFile.getSize())
                .build();
    }
}