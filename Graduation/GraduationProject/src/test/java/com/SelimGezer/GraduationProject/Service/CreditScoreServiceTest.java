package com.SelimGezer.GraduationProject.Service;

import com.SelimGezer.GraduationProject.Dtos.CreditResponceDto;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Enum.CreditResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreditScoreServiceTest {

    CreditScoreService creditScoreService;
    User user;

    @BeforeEach
    public void setUp(){
        creditScoreService=new CreditScoreService();
        user =new User();
        user.setFullname("Selim Gezer");
    }

    @Test
    void calculatePercentGuarantee() {
        BigDecimal result = creditScoreService.calculatePercentGuarantee(Optional.of(BigDecimal.valueOf(1000)), 20);
        assertEquals(BigDecimal.valueOf(200.0),result);
    }

    @Test
    void haveGuarantee() {
        boolean result = creditScoreService.haveGuarantee(Optional.of(BigDecimal.valueOf(5)));
        assertTrue(result);
    }

    @Test
    void creditLimitCalculate_whenCreditScoreSmall500() {
        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 100L);
        assertEquals(CreditResult.Reddedildi,creditResponceDto.getCreditResult());
    }

    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncomeSmall5000_WithGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(4500L));
        user.setGuaranteePrice(BigDecimal.valueOf(6000));

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(10600.0),creditResponceDto.getCreditLimit());
    }
    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncomeSmall5000_WithoutGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(4500L));
        user.setGuaranteePrice(null);

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(10000),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncome5000Beetween10000_WithGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(6000));
        user.setGuaranteePrice(BigDecimal.valueOf(1000));

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(20200.0),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncome5000Beetween10000_WithoutGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(6000));
        user.setGuaranteePrice(null);

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(20000),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncomeGreather10000_WithGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(12000));
        user.setGuaranteePrice(BigDecimal.valueOf(1000));

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(24250.0),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScore500Between1000_MonthlyIncomeGreather10000_WithoutGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(12000));
        user.setGuaranteePrice(null);

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 600L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(24000.0),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScoreGreather1000_WithGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(12000));
        user.setGuaranteePrice(BigDecimal.valueOf(1000));

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 1200L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(48500.0),creditResponceDto.getCreditLimit());
    }

    @Test
    void creditLimitCalculate_whenCreditScoreGreather1000_WithoutGuarantee() {
        user.setMonthlyIncome(BigDecimal.valueOf(12000));
        user.setGuaranteePrice(null);

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, 1200L);
        assertEquals(CreditResult.Onaylandi,creditResponceDto.getCreditResult());
        assertEquals(BigDecimal.valueOf(48000.0),creditResponceDto.getCreditLimit());
    }
}