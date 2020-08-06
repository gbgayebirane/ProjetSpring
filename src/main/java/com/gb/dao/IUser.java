package com.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.entities.User;

@Repository
public interface IUser extends JpaRepository<User, Integer>{

}
