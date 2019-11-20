package com.example.architecture.impl.v1.file.ftp;


import com.example.architecture.impl.v1.file.exception.FTPDirectoryNotFound;
import com.example.architecture.impl.v1.file.exception.FTPErrosSavingFile;
import com.example.architecture.impl.v1.file.mapper.FileMapper;
import com.example.architecture.impl.v1.user.UserModel;
import org.apache.commons.net.ftp.FTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FTPService {
    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    private FTPFile ftpFile;
    private FTPClient ftpClientGetInstance() throws IOException{
        FTPClient ftp =  new FTPClient();
        try {
            ftp.connect(host);
            ftp.login(username, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setFileTransferMode(FTP.COMPRESSED_TRANSFER_MODE);
            return ftp;
        }catch (Exception e){
            throw new IOException(e.getMessage());
        }
    }

    public boolean uploadFile(UserModel owner, MultipartFile fileToSave)throws  IOException{
        FTPClient ftpClient;
        try {
            ftpClient = ftpClientGetInstance();
            if(directoryExists(owner)){
                ftpClient.changeWorkingDirectory(owner.getId());
                return ftpClient.storeFile(fileToSave.getOriginalFilename(), fileToSave.getInputStream());
            }
            ftpClient.makeDirectory(owner.getId());
            ftpClient.changeWorkingDirectory(owner.getId());
            return ftpClient.storeFile(fileToSave.getOriginalFilename(), fileToSave.getInputStream());
        }catch (IOException e) {
            throw new FTPErrosSavingFile(e.getMessage());
        }
    }


    public boolean directoryExists(UserModel user) throws IOException{
        long directory = Arrays.stream(ftpClientGetInstance().listDirectories()).filter(ftpFile1 -> ftpFile1.getName().equalsIgnoreCase(user.getId())).count();
        return directory > 0;
    }

    private void clearDirectory(String idUser) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = ftpClientGetInstance();
            ftpClient.changeWorkingDirectory(idUser);
            List<FTPFile> fileInDirectory = Arrays.stream(ftpClient.listFiles()).collect(Collectors.toList());

            for (FTPFile file : fileInDirectory){
                ftpClient.deleteFile(file.getName());
            }
        }catch (IOException e){
            throw new FTPDirectoryNotFound(e.getMessage());
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public boolean deleteFile(UserModel user, String fileName) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = ftpClientGetInstance();
            if (directoryExists(user)) {
                ftpClient.changeWorkingDirectory(user.getId());
                return ftpClient.deleteFile(fileName);
            }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
        return true;
    }

    public boolean deleteDirectory(UserModel user)throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = ftpClientGetInstance();
            if (directoryExists(user)){
                clearDirectory(user.getId());
                return ftpClientGetInstance().removeDirectory(user.getId());
            }
            return true;
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public List<com.example.architecture.impl.v1.file.model.FTPFile> searchFileByName(UserModel owner) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = ftpClientGetInstance();
            if (directoryExists(owner)){
                ftpClient.changeWorkingDirectory(owner.getId());
                return Arrays.stream(ftpClient.listFiles()).map(FileMapper::mapToFTPFile).collect(Collectors.toList());
            }
            throw new FTPDirectoryNotFound();
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}