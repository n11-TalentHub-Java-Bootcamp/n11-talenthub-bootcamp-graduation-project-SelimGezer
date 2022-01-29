package com.SelimGezer.GraduationProject.Service.EntityService;

import com.SelimGezer.GraduationProject.Dao.UserDao;
import com.SelimGezer.GraduationProject.Dtos.UserRequestDto;
import com.SelimGezer.GraduationProject.Dtos.UserUpdateDto;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityServiceTest {

    private UserEntityService userEntityService;
    @Mock
    private UserDao userDao;
    @Mock
    private CreditEntityService creditEntityService;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userEntityService=new UserEntityService(userDao,creditEntityService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canSaveUser() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setIdentificationNumber(5L);
        userRequestDto.setFullname("selim gezer");
        userRequestDto.setMonthlyIncome(BigDecimal.valueOf(9800));
        userRequestDto.setPhoneNumber("+905386945400");
        User user= UserMapper.toEntity(userRequestDto);

        userDao.save(user);
        creditEntityService.saveCredit(user);
        Mockito.when(userDao.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, ()-> userEntityService.saveUser(userRequestDto));

        Mockito.verify(userDao).save(user);
        Mockito.verify(creditEntityService).saveCredit(user);
    }

    @Test
    void willThrowSaveUser() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setIdentificationNumber(5L);
        userRequestDto.setFullname("selim gezer");
        userRequestDto.setMonthlyIncome(BigDecimal.valueOf(9800));
        userRequestDto.setPhoneNumber("+905386945400");
        User user= UserMapper.toEntity(userRequestDto);
        userDao.save(user);

        Mockito.when(userDao.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.of(user));

        assertThrowsExactly(RuntimeException.class, ()-> userEntityService.saveUser(userRequestDto));
    }

    @Test
    void getUserByIdentificationNumber() {
        User user=new User();
        user.setIdentificationNumber(5L);
        user.setFullname("selim gezer");
        user.setMonthlyIncome(BigDecimal.valueOf(9800));
        user.setPhoneNumber("+905386945400");

        userDao.save(user);
        Mockito.when(userDao.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.of(user));

        userEntityService.getUserByIdentificationNumber(user.getIdentificationNumber());
    }

    @Test
    void getAllUser() {
        List<User> userList=new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        Mockito.when(userDao.findAll()).thenReturn(userList);

        assertEquals(userList.size(),userEntityService.getAllUser().size());
    }

    @Test
    void willThrowWhenUserDoesNotExist() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setIdentificationNumber(1L);
        assertThrows(RuntimeException.class, ()-> userEntityService.checkUserByIdentificationNumber(userRequestDto.getIdentificationNumber()));
    }

    @Test
    void whenUserDoesExist() {
        Long identificationNumber=12312312456L;
        User user=new User();
        user.setIdentificationNumber(identificationNumber);

        Mockito.when(userDao.findByIdentificationNumber(identificationNumber)).thenReturn(Optional.of(user));

        User result = userEntityService.checkUserByIdentificationNumber(identificationNumber);
        assertEquals(user,result);
    }

    @Test
    void updateUserByIdentificationNumber() {
        User user=new User();
        user.setIdentificationNumber(5L);
        user.setFullname("selim gezer");
        user.setMonthlyIncome(BigDecimal.valueOf(9800));
        user.setPhoneNumber("+905386945400");

        UserUpdateDto userUpdateDto=new UserUpdateDto();
        userUpdateDto.setFullname("selim gezer güncel");
        userUpdateDto.setPhoneNumber("+90538694540");
        userUpdateDto.setMonthlyIncome(BigDecimal.valueOf(12500));
        userUpdateDto.setGuaranteePrice(BigDecimal.valueOf(15000));

        Mockito.when(userDao.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.of(user));
        Mockito.when(userDao.save(user)).thenReturn(user);

        userEntityService.updateUserByIdentificationNumber(user.getIdentificationNumber(), userUpdateDto);

        Mockito.verify(creditEntityService).updateCredit(user);
    }

    @Test
    void deleteUserByIdentificationNumber() {
        User user=new User();
        user.setIdentificationNumber(5L);
        user.setFullname("selim gezer");
        user.setMonthlyIncome(BigDecimal.valueOf(9800));
        user.setPhoneNumber("+905386945400");

        Mockito.when(userDao.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.of(user));

        userEntityService.deleteUserByIdentificationNumber(user.getIdentificationNumber());
        Mockito.verify(userDao).delete(user);

    }
}