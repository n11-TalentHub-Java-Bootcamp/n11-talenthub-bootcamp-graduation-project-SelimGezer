package com.SelimGezer.GraduationProject.Dao;

import com.SelimGezer.GraduationProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByIdentificationNumber(Long id);
}
