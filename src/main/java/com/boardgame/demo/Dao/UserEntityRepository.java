package com.boardgame.demo.dao;

import com.boardgame.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    
}