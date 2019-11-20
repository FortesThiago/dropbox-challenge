package com.example.architecture.impl.v1;

import com.example.architecture.impl.v1.file.FileFacade;
import com.example.architecture.impl.v1.file.model.FTPFile;
import com.example.architecture.impl.v1.file.model.FileModel;
import com.example.architecture.impl.v1.user.UserFacade;
import com.example.architecture.impl.v1.user.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class ImplFacade {
    private FileFacade fileFacade;
    private UserFacade userFacade;

    public UserModel findById(String id) {
        return userFacade.findById(id);
    }

    public UserModel updateUser(String id, UserModel user){
        return userFacade.updateUser(id, user);
    }

    public List<UserModel> findAllUsers(){
        return userFacade.findAllUsers();
    }

    public UserModel create(UserModel user) {
        return userFacade.create(user);
    }

    public boolean deleteUserById(String id){
        userFacade.deleteUserById(id);
        return true;
    }

    public FileModel uploadFile(String owner, MultipartFile fileToSave) throws IOException {
        return userFacade.addFile(owner, fileFacade.uploadFile(findById(owner), fileToSave));
    }

    public void deleteFile(String owner, String fileId) throws IOException {
         fileFacade.deleteFile(findById(owner), fileId);
    }

    public Page<FTPFile> searchFiles(String ownerId, String fileName, Pageable pageable) throws IOException {
        return fileFacade.searchFiles(findById(ownerId), fileName, pageable);
    }
}

