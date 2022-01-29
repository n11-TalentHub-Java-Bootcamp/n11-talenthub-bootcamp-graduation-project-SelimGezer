package com.SelimGezer.GraduationProject.Dtos;

import com.SelimGezer.GraduationProject.Enum.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditResponceDto {

    private BigDecimal creditLimit;

    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;

}
