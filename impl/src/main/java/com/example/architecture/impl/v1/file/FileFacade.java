package com.example.architecture.impl.v1.file;

import com.example.architecture.impl.v1.file.model.FTPFile;
import com.example.architecture.impl.v1.file.model.FileModel;
import com.example.architecture.impl.v1.file.service.FileService;
import com.example.architecture.impl.v1.user.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FileFacade {
    private FileService service;

    public FileModel uploadFile(UserModel owner, MultipartFile fileToSave) throws IOException {
        return service.uploadFile(owner, fileToSave);
    }

    public boolean deleteFile(UserModel owner, String fileId) throws IOException {
        return service.deleteFile(owner, fileId);
    }

    public Page<FTPFile> searchFiles(UserModel owner, String  filename, Pageable pageable) throws IOException {
        return service.searchFileByName(owner, filename, pageable);
    }

    public void deleteDirectory(UserModel owner){

    }
}