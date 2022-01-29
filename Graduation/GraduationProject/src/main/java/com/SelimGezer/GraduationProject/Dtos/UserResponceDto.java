package com.SelimGezer.GraduationProject.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
public class UserResponceDto {

    private Long identificationNumber;

    private String fullname;

    private BigDecimal monthlyIncome;

    private String phoneNumber;

    private Date birthday;

    private Optional<BigDecimal> guaranteePrice;

    public void setGuaranteePrice(Optional<BigDecimal> guaranteePrice) {
        this.guaranteePrice = guaranteePrice;
    }
}
