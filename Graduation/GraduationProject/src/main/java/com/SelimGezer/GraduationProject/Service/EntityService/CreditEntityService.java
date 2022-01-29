package com.SelimGezer.GraduationProject.Service.EntityService;

import com.SelimGezer.GraduationProject.Dao.CreditDao;
import com.SelimGezer.GraduationProject.Dtos.CreditDetailResponceDto;
import com.SelimGezer.GraduationProject.Dtos.CreditResponceDto;
import com.SelimGezer.GraduationProject.Entity.Credit;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Exception.CreditNotFound;
import com.SelimGezer.GraduationProject.Mapper.CreditMapper;
import com.SelimGezer.GraduationProject.Service.CreditScoreService;
import com.SelimGezer.GraduationProject.Sms.SmsModel;
import com.SelimGezer.GraduationProject.Sms.TwilloSmsSenderService;
import com.SelimGezer.GraduationProject.Util.CreditScoreCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class CreditEntityService {

    private final CreditDao creditDao;
    private final CreditScoreService creditScoreService;
    private final TwilloSmsSenderService twilloSmsSenderService;

    public CreditEntityService(CreditDao creditDao, CreditScoreService creditScoreService, TwilloSmsSenderService twilloSmsSenderService) {
        this.creditDao = creditDao;
        this.creditScoreService = creditScoreService;
        this.twilloSmsSenderService = twilloSmsSenderService;
    }

    public CreditDetailResponceDto getByCreditIdentificationAndBirthday(Long identificationNumber, Date birthday) {
        Credit credit = creditDao.findCredit(identificationNumber, birthday);

        if(credit==null){
            log.error("getByCreditIdentificationAndBirthday methodu credit değeri null dönmektedir.");
            throw new CreditNotFound("Mevcut bilgilerle eşleşen bir kredi bulunmamaktadır!");
        }

        CreditDetailResponceDto creditDetailResponceDto =new CreditDetailResponceDto(credit);

        log.info("getByCreditIdentificationAndBirthday methodu başarı ile çalıştı.");
        return creditDetailResponceDto;
    }

    @Transactional
    public CreditResponceDto saveCredit(User user) {

        Long creditScore = CreditScoreCalculator.calculate(user.getIdentificationNumber());
        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, creditScore);
        Credit credit = CreditMapper.toEntity(user, creditResponceDto);
        creditDao.save(credit);

        SmsModel smsModel=new SmsModel(user.getPhoneNumber(),"Bu bir test mesajıdır.Kredi başvurunuz işleme alınmıştır."); //Todo aktifleştir
        twilloSmsSenderService.sendMessage(smsModel);

        log.info("saveCredit methodu başarı ile çalıştı.");
        return creditResponceDto;

    }

    @Transactional
    public CreditResponceDto updateCredit(User user){
        Credit credit=user.getCredit();
        Long creditScore = CreditScoreCalculator.calculate(user.getIdentificationNumber());

        CreditResponceDto creditResponceDto = creditScoreService.creditLimitCalculate(user, creditScore);
        credit.setCreditLimit(creditResponceDto.getCreditLimit());
        credit.setCreditResult(creditResponceDto.getCreditResult());

        creditDao.save(credit);
        log.info("updateCredit methodu başarı ile çalıştı.");
        return creditResponceDto;
    }
}
