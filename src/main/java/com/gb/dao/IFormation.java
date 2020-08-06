package com.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.entities.Formation;

@Repository
public interface IFormation extends JpaRepository<Formation, Integer> {

}
