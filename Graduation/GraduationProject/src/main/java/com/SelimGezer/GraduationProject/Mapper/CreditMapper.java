package com.SelimGezer.GraduationProject.Mapper;

import com.SelimGezer.GraduationProject.Dtos.CreditResponceDto;
import com.SelimGezer.GraduationProject.Entity.Credit;
import com.SelimGezer.GraduationProject.Entity.User;

public class CreditMapper {

    public static Credit toEntity(User user, CreditResponceDto creditResponceDto){
        Credit credit=new Credit(user,creditResponceDto.getCreditLimit(),creditResponceDto.getCreditResult());
        return credit;
    }
}
