package com.example.h2crud.service;

import com.example.h2crud.dto.TutorialRequestDTO;
import com.example.h2crud.dto.TutorialResponseDTO;

import java.util.List;

public interface ITutorialService {
    List<TutorialResponseDTO> findAll(String title);

    TutorialResponseDTO getTutorialById(Long id);

    void saveTutorial(TutorialRequestDTO tutorialRequestDTO);

    TutorialResponseDTO updateTutorial(Long id, TutorialRequestDTO tutorialRequestDTO);

    void deleteTutorial(Long id);

    List<TutorialResponseDTO> findByPublished(Boolean published);

}