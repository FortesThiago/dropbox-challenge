package com.example.architecture.impl.v1.file.exception;

public class FTPFileNotFound extends RuntimeException{
    public FTPFileNotFound(){
        super("File not found ");
    }

    public FTPFileNotFound(String message){
        super(message);
    }
}