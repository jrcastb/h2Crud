package com.example.h2crud.repository;

import com.example.h2crud.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(Boolean published);
    List<Tutorial> findByTitleContaining(String title);
    /*
    @Query(value = "SELECT t FROM Tutorial t")//NO ES SQL - ESTO ES JPQL
    List<Tutorial> findAllTutorials();
    */
}
