package com.SelimGezer.GraduationProject.Service.EntityService;

import com.SelimGezer.GraduationProject.Dao.CreditDao;
import com.SelimGezer.GraduationProject.Dtos.CreditDetailResponceDto;
import com.SelimGezer.GraduationProject.Dtos.CreditResponceDto;
import com.SelimGezer.GraduationProject.Entity.Credit;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Enum.CreditResult;
import com.SelimGezer.GraduationProject.Exception.CreditNotFound;
import com.SelimGezer.GraduationProject.Mapper.CreditMapper;
import com.SelimGezer.GraduationProject.Service.CreditScoreService;
import com.SelimGezer.GraduationProject.Sms.SmsModel;
import com.SelimGezer.GraduationProject.Sms.TwilloSmsSenderService;
import com.SelimGezer.GraduationProject.Util.CreditScoreCalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditEntityServiceTest {

    @InjectMocks
    private  CreditEntityService creditEntityService;
    @Mock
    private  CreditDao creditDao;
    @Mock
    private  CreditScoreService creditScoreService;
    @Mock
    private  TwilloSmsSenderService twilloSmsSenderService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
         autoCloseable = MockitoAnnotations.openMocks(this);
         creditEntityService=new CreditEntityService(creditDao,creditScoreService,twilloSmsSenderService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetByCreditIdentificationAndBirthday() {
        Long identificationNumber=12312312456L;
        Date date=new Date();

        Credit credit=new Credit();
        credit.setCreditResult(CreditResult.Onaylandi);
        credit.setCreditLimit(BigDecimal.valueOf(12500));

        Mockito.when(creditDao.findCredit(identificationNumber,date)).thenReturn(credit);

        CreditDetailResponceDto creditDetailResponceDto=new CreditDetailResponceDto(credit);
        CreditDetailResponceDto byCreditIdentificationAndBirthday = creditEntityService.getByCreditIdentificationAndBirthday(identificationNumber, date);

        assertEquals(creditDetailResponceDto,byCreditIdentificationAndBirthday);
    }

    @Test
    void willThrowWhenCreditNull() {
        Long identificationNumber=2456L;
        Date date=new Date();
        assertThrowsExactly(CreditNotFound.class, ()-> creditEntityService.getByCreditIdentificationAndBirthday(identificationNumber, date));
    }
    @Test
    void canSaveCredit() {
        User user=new User();
        user.setIdentificationNumber(12312312456L);
        user.setFullname("Selim Gezer");
        user.setMonthlyIncome(BigDecimal.valueOf(9800));
        user.setGuaranteePrice(BigDecimal.valueOf(1000));
        user.setPhoneNumber("+905386945400");

        Long creditScore=0L;
        CreditResponceDto creditResponceDto=new CreditResponceDto();
        Credit credit=CreditMapper.toEntity(user,creditResponceDto);

        creditScore=CreditScoreCalculator.calculate(user.getIdentificationNumber());

        Mockito.when(creditScoreService.creditLimitCalculate(user,creditScore)).thenReturn(creditResponceDto);
        Mockito.when(creditDao.save(credit)).thenReturn(credit);

        CreditResponceDto creditResponceActual = creditEntityService.saveCredit(user);

        assertEquals(creditResponceDto,creditResponceActual);

        Mockito.verify(creditDao).save(Mockito.any(Credit.class));
        Mockito.verify(creditScoreService).creditLimitCalculate(user,creditScore);
        Mockito.verify(twilloSmsSenderService).sendMessage(new SmsModel(user.getPhoneNumber(),"Bu bir test mesajıdır.Kredi başvurunuz işleme alınmıştır."));
    }

    @Test
    void canUpdateCredit() {
        User user=new User();
        user.setIdentificationNumber(12312312456L);
        user.setFullname("Selim Gezer");
        user.setMonthlyIncome(BigDecimal.valueOf(9800));
        user.setGuaranteePrice(BigDecimal.valueOf(1000));


        Long creditScore=0L;
        creditScore=CreditScoreCalculator.calculate(user.getIdentificationNumber());

        CreditResponceDto creditResponceDto=new CreditResponceDto();
        creditResponceDto.setCreditLimit(BigDecimal.valueOf(15200));
        creditResponceDto.setCreditResult(CreditResult.Onaylandi);

        Credit credit=CreditMapper.toEntity(user,creditResponceDto);
        user.setCredit(credit);

        Mockito.when(creditScoreService.creditLimitCalculate(user,creditScore)).thenReturn(creditResponceDto);
        Mockito.when(creditDao.save(credit)).thenReturn(credit);

        CreditResponceDto creditResponceActual = creditEntityService.updateCredit(user);

        assertEquals(creditResponceDto,creditResponceActual);

        Mockito.verify(creditScoreService).creditLimitCalculate(user,creditScore);
        Mockito.verify(creditDao).save(Mockito.any(Credit.class));

    }
}