package com.SelimGezer.GraduationProject.Service;

import com.SelimGezer.GraduationProject.Dtos.CreditResponceDto;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Enum.CreditResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Slf4j
@Service
public class CreditScoreService {

    private float CREDIT_COEFFICIENT=4.0F;

    public BigDecimal calculatePercentGuarantee(Optional<BigDecimal> guaranteePrice, double percent){
        log.info(String.format("calculatePercentGuarantee methodu guarenteePrice=%s ve yüzde=%.2f ile çağrıldı.",guaranteePrice.toString(),percent));
        BigDecimal bigDecimal= guaranteePrice.get().multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100));
       return bigDecimal;
    }

    public boolean haveGuarantee(Optional<BigDecimal> guaranteePrice){
        log.info(String.format("haveGuarantee methodu guaranteePrice=%s değeri ile çağrıldı.",guaranteePrice.toString()));

        if(guaranteePrice.isPresent()){
            return true;
        }
        return false;
    }

    public CreditResponceDto creditLimitCalculate(User user, Long creditScore){
        log.info(String.format("creditLimitCalculate methodu fullname=%s ve creditScore=%s ile çağrıldı.",user.getFullname(),creditScore.toString()));
        CreditResponceDto creditResponceDto =new CreditResponceDto();
        BigDecimal monthlyIncome  = user.getMonthlyIncome();
        Optional<BigDecimal> guaranteePrice = user.getGuaranteePrice();
        BigDecimal creditLimit=new BigDecimal(BigInteger.ZERO);

        if(creditScore<500){
            creditResponceDto.setCreditResult(CreditResult.Reddedildi);
        }else if(creditScore>=500 && creditScore<1000 ){
            creditResponceDto.setCreditResult(CreditResult.Onaylandi);

            if(monthlyIncome.compareTo(BigDecimal.valueOf(5000))==-1){
                creditLimit=creditLimit.add(BigDecimal.valueOf(10_000));

                if(haveGuarantee(guaranteePrice))
                    creditLimit=creditLimit.add(calculatePercentGuarantee(guaranteePrice,10));

            }else if(monthlyIncome.compareTo(BigDecimal.valueOf(5000))==1 && monthlyIncome.compareTo(BigDecimal.valueOf(10_000))==-1){
                creditLimit=creditLimit.add(BigDecimal.valueOf(20_000));

                if(haveGuarantee(guaranteePrice))
                    creditLimit=creditLimit.add(calculatePercentGuarantee(guaranteePrice,20));
            }else if(monthlyIncome.compareTo(BigDecimal.valueOf(10_000))==1){
                creditLimit=creditLimit.add(monthlyIncome.multiply(BigDecimal.valueOf(CREDIT_COEFFICIENT)).divide(BigDecimal.valueOf(2)));

                if(haveGuarantee(guaranteePrice))
                    creditLimit=creditLimit.add(calculatePercentGuarantee(guaranteePrice,25));
            }

        }else{
            creditResponceDto.setCreditResult(CreditResult.Onaylandi);

            creditLimit=creditLimit.add(monthlyIncome.multiply(BigDecimal.valueOf(CREDIT_COEFFICIENT)));

            if(haveGuarantee(guaranteePrice))
                creditLimit=creditLimit.add(calculatePercentGuarantee(guaranteePrice,50));
        }

        creditResponceDto.setCreditLimit(creditLimit);

        log.info(String.format("creditLimitCalculate methodu başarı ile çalıştı. Geri dönüş değeri %s olarak verildi.",creditResponceDto));
        return creditResponceDto;
    }

}
