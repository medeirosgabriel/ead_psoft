package com.ufcg.lab2.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.lab2.entities.Discipline;

@Repository
public interface DisciplineRepository<T, ID extends Serializable> extends JpaRepository<Discipline, Long> {

}