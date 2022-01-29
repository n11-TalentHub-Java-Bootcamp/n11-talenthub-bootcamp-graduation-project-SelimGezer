package com.SelimGezer.GraduationProject.Mapper;

import com.SelimGezer.GraduationProject.Dtos.UserRequestDto;
import com.SelimGezer.GraduationProject.Dtos.UserResponceDto;
import com.SelimGezer.GraduationProject.Entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto userRequestDto ){
        User user =new User(
                userRequestDto.getIdentificationNumber(),
                userRequestDto.getFullname(),
                userRequestDto.getMonthlyIncome(),
                userRequestDto.getPhoneNumber(),
                userRequestDto.getBirthday(),
                userRequestDto.getGuaranteePrice()
                );

        return user;
    }

    public static UserResponceDto toDto(User user){
        UserResponceDto userResponceDto=new UserResponceDto(
                user.getIdentificationNumber(),
                user.getFullname(),
                user.getMonthlyIncome(),
                user.getPhoneNumber(),
                user.getBirthday(),
                user.getGuaranteePrice()
        );
        return userResponceDto;
    }
}
