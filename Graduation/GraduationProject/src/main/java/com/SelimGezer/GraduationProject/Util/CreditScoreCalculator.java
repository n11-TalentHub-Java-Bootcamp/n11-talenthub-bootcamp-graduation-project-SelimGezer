package com.SelimGezer.GraduationProject.Util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class CreditScoreCalculator {

    public static Long calculate(Long identificationNumber){
        String s = identificationNumber.toString();
        String substring = s.substring(s.length() - 3);
        Long l = Long.parseLong(substring);
        if(l<500){
            log.info("Kimlik numarasının son 3 hanesi 500'ün altında olan bir kullanıcı ile işlem gerçekleştirildi. Hesaplanan kredi skoru="+l);
            return l;
        }else{
            long coef = ThreadLocalRandom.current().nextLong(1, 4);
            l*=coef;
            log.info("Kimlik numarasının son 3 hanesi 500'ün üstünde olan bir kullanıcı ile işlem gerçekleştirildi. Hesaplanan kredi skoru="+l);
            return l;
        }
    }

}
