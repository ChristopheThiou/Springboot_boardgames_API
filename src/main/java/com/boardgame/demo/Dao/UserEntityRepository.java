package com.boardgame.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardgame.demo.Entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    
}