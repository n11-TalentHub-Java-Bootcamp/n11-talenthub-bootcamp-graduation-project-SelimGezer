package com.SelimGezer.GraduationProject.Util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditScoreCalculatorTest {

    @Test
    void calculate() {
        List<Long> longList=new ArrayList<>();
        longList.addAll(List.of(12312312456L,12312312789L));
        for (Long item:longList) {
            String s = item.toString();
            String substring = s.substring(s.length() - 3);
            Long last3Number = Long.parseLong(substring);
            if(last3Number<500){
                Assert.assertEquals(last3Number, CreditScoreCalculator.calculate(item));
            }else{
                Assert.assertTrue(CreditScoreCalculator.calculate(item)>500);
            }
        }
    }
}