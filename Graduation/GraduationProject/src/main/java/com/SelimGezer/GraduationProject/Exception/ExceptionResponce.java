package com.SelimGezer.GraduationProject.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponce {
    private Date errorDate;
    private String message;
    private String detail;
}
