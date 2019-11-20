package com.example.architecture.impl.v1.file.exception;

public class FTPDirectoryNotFound extends RuntimeException {
    public FTPDirectoryNotFound() {
        super("Directory not found");
    }

    public FTPDirectoryNotFound(String message) {
        super(message);
    }
}
