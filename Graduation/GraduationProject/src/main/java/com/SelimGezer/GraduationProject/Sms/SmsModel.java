package com.SelimGezer.GraduationProject.Sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsModel {
    private String mobileNumber;
    private String smsText;
}
