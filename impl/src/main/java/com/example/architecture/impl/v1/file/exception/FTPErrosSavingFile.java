package com.example.architecture.impl.v1.file.exception;

public class FTPErrosSavingFile extends RuntimeException {
    public FTPErrosSavingFile() {
        super("Error saving file");
    }

    public FTPErrosSavingFile(String message) {
        super(message);
    }
}
