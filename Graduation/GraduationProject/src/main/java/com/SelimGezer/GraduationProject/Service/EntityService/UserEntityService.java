package com.SelimGezer.GraduationProject.Service.EntityService;

import com.SelimGezer.GraduationProject.Dao.UserDao;
import com.SelimGezer.GraduationProject.Dtos.UserRequestDto;
import com.SelimGezer.GraduationProject.Dtos.UserResponceDto;
import com.SelimGezer.GraduationProject.Dtos.UserUpdateDto;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Exception.UserNotFound;
import com.SelimGezer.GraduationProject.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserEntityService {

    private final UserDao userDao;
    private final CreditEntityService creditEntityService;

    public UserEntityService(UserDao userDao, CreditEntityService creditEntityService) {
        this.userDao = userDao;
        this.creditEntityService = creditEntityService;
    }

    public UserResponceDto saveUser(UserRequestDto userRequestDto){
        Optional<User> optionalUser = userDao.findByIdentificationNumber(userRequestDto.getIdentificationNumber());
        if(optionalUser.isPresent()){
            log.info("saveUser methodu var olan bir kimlik numarası ile işlem gerçekleştirmeye çalıştı.");
            throw new RuntimeException("Var olan bir kimlik numarası kullanıldı! Lütfen kendi kimlik numaranız ile işlem gerçekleştiriniz!");
        }else{
            User user = UserMapper.toEntity(userRequestDto);
            User save = userDao.save(user);
            creditEntityService.saveCredit(save);
            log.info("saveUser methodu başarı ile çalıştı.");
            return UserMapper.toDto(save);
        }
    }

    public UserResponceDto getUserByIdentificationNumber(Long identificationNumber) {
        log.info("getUserByIdentificationNumber methodu başarı ile çalıştı.");
        return UserMapper.toDto(checkUserByIdentificationNumber(identificationNumber));
    }

    public List<UserResponceDto> getAllUser() {
        log.info("getAllUser methodu başarı ile çalıştı.");
        return userDao.findAll().stream().map(user -> UserMapper.toDto(user)).collect(Collectors.toList());
    }

    public User checkUserByIdentificationNumber(Long identificationNumber) throws UserNotFound{
        Optional<User> optionalUser = userDao.findByIdentificationNumber(identificationNumber);
        if(optionalUser.isPresent()){
            log.info("checkUserByIdentificationNumber methodu başarı ile çalıştı.");
            return optionalUser.get();
        }
        else{
            log.info("checkUserByIdentificationNumber methodu olmayan bir kimlik numarası tespit etti.");
            throw new UserNotFound(String.format("%d kimlik numarasına ait bir kullanıcı bulunamadı!",identificationNumber));
        }
    }

    public UserResponceDto updateUserByIdentificationNumber(Long identificationNumber, UserUpdateDto userUpdateDto) {
        User user = checkUserByIdentificationNumber(identificationNumber);

        user.setFullname(userUpdateDto.getFullname());
        user.setGuaranteePrice(userUpdateDto.getGuaranteePrice());
        user.setMonthlyIncome(userUpdateDto.getMonthlyIncome());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());

        User update = userDao.save(user);
        creditEntityService.updateCredit(update);
        log.info("updateUserByIdentificationNumber methodu başarı ile çalıştı.");
        return UserMapper.toDto(update);
    }

    public void deleteUserByIdentificationNumber(Long identificationNumber) {
        User user = checkUserByIdentificationNumber(identificationNumber);
        log.info("deleteUserByIdentificationNumber methodu başarı ile çalıştı.");
        userDao.delete(user);
    }

}
