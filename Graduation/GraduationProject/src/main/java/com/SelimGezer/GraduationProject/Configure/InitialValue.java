package com.SelimGezer.GraduationProject.Configure;

import com.SelimGezer.GraduationProject.Dao.CreditDao;
import com.SelimGezer.GraduationProject.Dao.UserDao;
import com.SelimGezer.GraduationProject.Entity.Credit;
import com.SelimGezer.GraduationProject.Entity.User;
import com.SelimGezer.GraduationProject.Enum.CreditResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class InitialValue implements CommandLineRunner {

    @Autowired
    UserDao userDao;
    @Autowired
    CreditDao creditDao;

    @Override
    public void run(String... args) throws Exception {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        User user=new User(12312312313L,"Selim Gezer",BigDecimal.valueOf(9800),"+905386945400",simpleDateFormat.parse("1997-08-17"), BigDecimal.valueOf(12000));
        User user2=new User(12312312514L,"Deniz Kumral",BigDecimal.valueOf(4500),"+905907305499",simpleDateFormat.parse("1999-10-02"), null);
        User user3=new User(12312312716L,"Ethem Turan",BigDecimal.valueOf(12000),"+905901231212",simpleDateFormat.parse("1980-05-15"), BigDecimal.valueOf(20000));

        userDao.saveAll(List.of(user,user2,user3));

        Credit credit=new Credit(user,BigDecimal.ZERO, CreditResult.Reddedildi);
        Credit credit2=new Credit(user2,BigDecimal.valueOf(18000), CreditResult.Onaylandi);
        Credit credit3=new Credit(user3,BigDecimal.valueOf(58000), CreditResult.Onaylandi);

        creditDao.saveAll(List.of(credit,credit2,credit3));
    }
}
