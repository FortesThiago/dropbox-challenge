package com.example.architecture.impl.v1.user.service;

import com.example.architecture.impl.v1.file.model.FileModel;
import com.example.architecture.impl.v1.user.exception.UserNotFound;
import com.example.architecture.impl.v1.user.mapper.UserMapper;
import com.example.architecture.impl.v1.user.UserModel;
import com.example.architecture.impl.v1.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;

    public UserModel findById(String id) {
        return UserMapper.mapToModel(repository.findById(id).orElseThrow(UserNotFound::new));
    }

    public UserModel create(UserModel user) {
        return UserMapper.mapToModel(repository.save(UserMapper.mapToEntity(user)));
    }

    public List<UserModel> findAllusers() {
        return repository.findAll().stream().map(UserMapper::mapToModel).collect(Collectors.toList());
    }

    public FileModel addFiles(String userId, FileModel file){
        UserModel userModel = UserMapper.mapToModel(repository.findById(userId).orElseThrow());
        userModel.setFiles(addFileList(userModel.getFiles(), file));
        repository.save(UserMapper.mapToEntity(userModel));
        return file;
    }

    public UserModel updateUser(String id, UserModel userModel){
        return repository.findById(id).map(userEntity -> {
            userEntity.setName(userModel.getName());
            userEntity.setEmail(userModel.getEmail());
            return repository.save(userEntity);
        }).map(UserMapper::mapToModel).orElseThrow();
    }

    public List<FileModel> addFileList(List<FileModel> files, FileModel fileModel){
        files.add(fileModel);
        return files;
    }


    public boolean deleteUserById(String id){
        repository.deleteById(id);
        return true;
    }
}