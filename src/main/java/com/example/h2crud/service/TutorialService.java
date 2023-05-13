package com.example.h2crud.service;

import com.example.h2crud.dto.TutorialRequestDTO;
import com.example.h2crud.dto.TutorialResponseDTO;
import com.example.h2crud.model.Tutorial;
import com.example.h2crud.repository.ITutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService implements ITutorialService{

    private final ITutorialRepository tutorialRepository;

    @Override
    public List<TutorialResponseDTO> findAll(String title) {
        List<Tutorial> tutorials;

        if (title.equals("")){
            tutorials = tutorialRepository.findAll();
        } else {
            tutorials = tutorialRepository.findByTitleContaining(title);
        }
        return tutorials.stream().map(this::mapToTutorialResponseDTO).toList();
    }


    @Override
    public TutorialResponseDTO getTutorialById(Long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        TutorialResponseDTO tutorialResponseDTO = new TutorialResponseDTO();
        if (tutorialData.isPresent()){
            tutorialResponseDTO = mapToTutorialResponseDTO(tutorialData.get());
        }
        return tutorialResponseDTO;
    }

    @Override
    public void saveTutorial(TutorialRequestDTO tutorialRequestDTO) {
        Tutorial tutorial = mapToTutorial(tutorialRequestDTO);
        try{
            tutorialRepository.save(tutorial);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public TutorialResponseDTO updateTutorial(Long id, TutorialRequestDTO tutorialRequestDTO) {
        return null;
    }

    @Override
    public void deleteTutorial(Long id) {

    }

    @Override
    public List<TutorialResponseDTO> findByPublished(Boolean published) {
        return null;
    }

    private Tutorial mapToTutorial(TutorialRequestDTO tutorialRequestDTO) {
        return Tutorial.builder()
                .title(tutorialRequestDTO.getTitle())
                .description(tutorialRequestDTO.getDescription())
                .published(tutorialRequestDTO.getPublished())
                .build();
    }
    private TutorialResponseDTO mapToTutorialResponseDTO(Tutorial tutorial) {
        return TutorialResponseDTO.builder()
                .id(tutorial.getId())
                .title(tutorial.getTitle())
                .description(tutorial.getDescription())
                .published(tutorial.getPublished())
                .build();
    }
}
