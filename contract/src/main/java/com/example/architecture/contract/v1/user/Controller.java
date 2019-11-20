package com.example.architecture.contract.v1.user;

import com.example.architecture.contract.v1.user.model.request.UserRequest;
import com.example.architecture.contract.v1.user.model.response.FTPFileResponse;
import com.example.architecture.contract.v1.user.model.response.FileResponse;
import com.example.architecture.contract.v1.user.model.response.UserResponse;
import com.example.architecture.impl.v1.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
@Api(value = "API REST Dropbox")
@RequestMapping("/v1/users")
@RestController
@AllArgsConstructor
public class Controller {
    private UserContractFacade facade;
    @ApiOperation(value = "Return a user from his id")
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable String id) {
        return facade.findById(id);
    }

    @ApiOperation(value = "Save an user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest user) {
        return facade.create(user);
    }

    @ApiOperation(value = "Find all users saved")
    @GetMapping
    public List<UserResponse> findAllUsers() {
        return facade.findAllUsers();
    }

    @ApiOperation(value = "Delete an user from his id")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
     public void deleteUserById(@PathVariable String id){
        facade.deleteUserById(id);
    }

    @ApiOperation(value = "Update an user from his id")
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserModel updateUser(@PathVariable String id, @RequestBody UserModel userModel){
        return facade.updateUser(id, userModel);
    }

    @ApiOperation(value = "Save a file on FTP server to a user")
    @PostMapping("/{id}/files")
    @ResponseStatus(value = HttpStatus.OK)
    public FileResponse uploadFile(@PathVariable String id, @RequestParam MultipartFile file) throws IOException {
        return facade.uploadFile(id, file);
    }

    @ApiOperation(value = "Delete files from user by id")
    @DeleteMapping("/{ownerId}/files/{fileId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable String ownerId, @PathVariable String fileId) throws IOException {
        facade.deleteFile(ownerId, fileId);
    }

    @ApiOperation(value = "Search files from a user")
    @GetMapping("/{ownerId}/files")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<FTPFileResponse> searchFiles(@PathVariable String ownerId, @RequestParam(required = false) String fileName, Pageable pageable) throws IOException {
        return facade.searchFiles(ownerId, fileName, pageable);
    }
}