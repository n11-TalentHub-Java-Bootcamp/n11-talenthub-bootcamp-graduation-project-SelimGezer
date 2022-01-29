package com.SelimGezer.GraduationProject.Dtos;

import com.SelimGezer.GraduationProject.CustomValidation.IdentificationNumber;
import com.SelimGezer.GraduationProject.CustomValidation.PhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class UserRequestDto {

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @IdentificationNumber(message = "Lütfen 11 haneli,çift ve geçerli bir kimlik numarası giriniz!")
    private Long identificationNumber;

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @Size(min = 7,max = 50,message ="Lütfen {min} ile {max} arasında uzunluğa sahip bir giriş yapınız!")
    private String fullname;

    @Positive(message = "Lütfen geçerli bir değer girişi gerçekleştiriniz!")
    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @Digits(integer = 19,fraction = 2,message = "Lütfen vigülden sonra max {fraction} hane olacak şekilde değer giriniz!")
    private BigDecimal monthlyIncome;

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @PhoneNumber(message ="Lütfen telefon numaranızı eksiksiz ve alan koduyla birlikte yazınız!")
    private String phoneNumber;

    @NotNull(message = "Boş değer girişine izin verilmemektedir!")
    @Past(message = "Lütfen doğum tarihinizi doğru giriniz!")
    private Date birthday;

    @Positive(message = "Lütfen geçerli bir değer girişi gerçekleştiriniz!")
    @Digits(integer = 19,fraction = 2,message = "Lütfen vigülden sonra max {fraction} hane olacak şekilde değer giriniz!")
    private BigDecimal guaranteePrice;

}
