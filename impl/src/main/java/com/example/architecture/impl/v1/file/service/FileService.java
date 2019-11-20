package com.example.architecture.impl.v1.file.service;

import com.example.architecture.impl.v1.file.FileRepository;
import com.example.architecture.impl.v1.file.exception.FTPFileNotFound;
import com.example.architecture.impl.v1.file.ftp.FTPService;
import com.example.architecture.impl.v1.file.mapper.FileMapper;
import com.example.architecture.impl.v1.file.model.FTPFile;
import com.example.architecture.impl.v1.file.model.FileModel;
import com.example.architecture.impl.v1.user.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
    private FTPService ftpService;
    private FileRepository fileRepository;

    public FileModel uploadFile(UserModel owner, MultipartFile file) throws IOException {
        try {
            ftpService.uploadFile(owner, file);
            FileModel fileSaved = FileMapper.mapToFileModel(fileRepository.save(FileMapper.mapToEntity(FileModel.builder()
                    .idOwner(owner.getId())
                    .originalName(file.getOriginalFilename())
                    .build())));
            fileSaved.setFtpName(fileSaved.getId() + file.getOriginalFilename());
            return fileSaved;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private FileModel getFile(List<FileModel> files, String fileId) {
        return files.stream().filter(file -> file.getId().equalsIgnoreCase(fileId)).findFirst().orElseThrow(FTPFileNotFound::new);
    }

    public boolean deleteFile(UserModel owner, String fileId) throws IOException {
        FileModel fileModel = getFile(owner.getFiles(), fileId);
        try {
            if (ftpService.deleteFile(owner, fileModel.getFtpName())) {
                return true;
            }
            throw new IOException();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private <T> Page<T> paginateList(List<T> listoToPaginate, Pageable pageable){
        int itemQuantity = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = itemQuantity * currentPage;

        List<T> files;
        if (listoToPaginate.size() < startItem){
            files= List.of();
        }else{
            int toIndex = Math.min(startItem + itemQuantity, listoToPaginate.size());
            files = listoToPaginate.subList(startItem, toIndex);
        }
        return new PageImpl<>(files, pageable, listoToPaginate.size());
    }

    public Page<FTPFile> searchFileByName(UserModel owner, String fileName, Pageable pageable) throws IOException {
        if (fileName == null)
            return paginateList(listAllFilesFromuser(owner), pageable);
        return paginateList(listAllFilesFromuser(owner).stream().filter(FTPFile -> FTPFile.getName().contains(fileName)).collect(Collectors.toList()), pageable);
    }

    private List<FTPFile> listAllFilesFromuser(UserModel user) throws IOException {
        try {
            List<FTPFile> userFiles = ftpService.searchFileByName(user);
            if (userFiles.isEmpty())
                throw new FTPFileNotFound();
            return userFiles;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}