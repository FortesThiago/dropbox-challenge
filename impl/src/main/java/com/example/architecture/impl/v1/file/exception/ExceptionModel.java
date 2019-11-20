package com.example.architecture.impl.v1.file.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionModel {
    private Date timestamp;
    private String message;
}
