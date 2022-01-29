package com.SelimGezer.GraduationProject.Dao;

import com.SelimGezer.GraduationProject.Entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface CreditDao extends JpaRepository<Credit,Long> {

    @Query("select credit from Credit credit JOIN credit.user user where user.identificationNumber= :id and user.birthday= :birthday")
    Credit findCredit(@Param("id") Long id, @Param("birthday") Date birthday);


}
