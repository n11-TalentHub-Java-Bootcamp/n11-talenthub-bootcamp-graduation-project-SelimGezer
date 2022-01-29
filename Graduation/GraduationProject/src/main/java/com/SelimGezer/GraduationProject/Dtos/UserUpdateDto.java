package com.SelimGezer.GraduationProject.Dtos;

import com.SelimGezer.GraduationProject.CustomValidation.PhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class UserUpdateDto {

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @Size(min = 10,max = 50,message ="Lütfen {min} ile {max} arasında uzunluğa sahip bir giriş yapınız!")
    String fullname;

    @Positive(message = "Lütfen geçerli bir değer girişi gerçekleştiriniz!")
    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @Digits(integer = 19,fraction = 2,message = "Lütfen vigülden sonra max {fraction} hane olacak şekilde değer giriniz!")
    BigDecimal monthlyIncome;

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @PhoneNumber(message ="Lütfen telefon numaranızı eksiksiz ve alan koduyla birlikte yazınız!")
    String phoneNumber;

    @Positive(message = "Lütfen geçerli bir değer girişi gerçekleştiriniz!")
    @Digits(integer = 19,fraction = 2,message = "Lütfen vigülden sonra max {fraction} hane olacak şekilde değer giriniz!")
    BigDecimal guaranteePrice;

}
