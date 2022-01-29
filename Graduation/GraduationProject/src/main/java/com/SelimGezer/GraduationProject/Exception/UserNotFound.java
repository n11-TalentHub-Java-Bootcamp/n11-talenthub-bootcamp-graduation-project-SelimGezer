package com.SelimGezer.GraduationProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "User not found")
public class UserNotFound extends RuntimeException {

    public UserNotFound(String message) {
        super(message);
    }
}
