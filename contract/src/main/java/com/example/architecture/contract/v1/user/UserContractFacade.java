package com.example.architecture.contract.v1.user;

import com.example.architecture.contract.v1.user.model.response.FTPFileResponse;
import com.example.architecture.contract.v1.user.model.response.FileResponse;
import com.example.architecture.contract.v1.user.mapper.FileMapper;
import com.example.architecture.contract.v1.user.mapper.UserMapper;
import com.example.architecture.contract.v1.user.model.request.UserRequest;
import com.example.architecture.contract.v1.user.model.response.UserResponse;
import com.example.architecture.impl.v1.ImplFacade;
import com.example.architecture.impl.v1.user.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserContractFacade {
    private ImplFacade implFacade;

    UserResponse findById(String id) {
        return UserMapper.mapToContract(implFacade.findById(id));
    }

    UserResponse create(UserRequest user) {
        return UserMapper.mapToContract(implFacade.create(UserMapper.mapToImpl(user)));
    }

     List<UserResponse> findAllUsers(){
        return implFacade.findAllUsers().stream()
                .map(UserMapper::mapToContract)
                .collect(Collectors.toList());
    }

    void deleteUserById(String id){
        implFacade.deleteUserById(id);
    }

    UserModel updateUser(String id, UserModel user){
        return implFacade.updateUser(id, user);
    }

    public FileResponse uploadFile(String owner, MultipartFile fileToSave) throws IOException {
        return FileMapper.mapToContract(implFacade.uploadFile(owner, fileToSave));
    }

    public void deleteFile(String owner, String fileId) throws IOException {
         implFacade.deleteFile(owner, fileId);
    }

    Page<FTPFileResponse> searchFiles(String ownerId, String fileName, Pageable pageable) throws IOException {
        return implFacade.searchFiles(ownerId, fileName, pageable).map(FileMapper::mapToFTPFileResponse);
    }
}