package com.example.architecture.impl.v1.user;


import com.example.architecture.impl.v1.file.model.FileModel;
import com.example.architecture.impl.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {
    private UserService service;

    public UserModel findById(String id) {
        return service.findById(id);
    }

    public UserModel updateUser(String id, UserModel user){
        return service.updateUser(id, user);
    }

    public List<UserModel> findAllUsers(){
        return service.findAllusers();
    }

    public UserModel create(UserModel user) {
        return service.create(user);
    }

    public FileModel addFile(String user, FileModel fileAdd){
        return service.addFiles(user, fileAdd);
    }

    public boolean deleteUserById(String id){
        service.deleteUserById(id);
        return true;
    }
}
