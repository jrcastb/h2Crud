package com.example.h2crud.controller;

import com.example.h2crud.dto.TutorialRequestDTO;
import com.example.h2crud.dto.TutorialResponseDTO;
import com.example.h2crud.service.ITutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutorials")
@RequiredArgsConstructor
public class TutorialController {

    private final ITutorialService tutorialService;

    //READ
    @GetMapping()
    public ResponseEntity<List<TutorialResponseDTO>> getAllTutorials(@RequestParam(required = false) String title){
        ResponseEntity<List<TutorialResponseDTO>> response;
        List<TutorialResponseDTO> tutorials = tutorialService.findAll(title);
        if (tutorials.isEmpty()){
            response = new ResponseEntity<>(tutorials, HttpStatus.NO_CONTENT);
        }else {
            response = new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
        return response;
    }
    //READ
    @GetMapping("/{id}")
    public ResponseEntity<TutorialResponseDTO> getTutorialById(@PathVariable(value = "id") Long id){
        ResponseEntity<TutorialResponseDTO> response;
        TutorialResponseDTO tutorialResponse = tutorialService.getTutorialById(id);
        if (tutorialResponse != null){
            response = new ResponseEntity<>(tutorialResponse, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }
    //CREATE
    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveTutorial(@RequestBody TutorialRequestDTO tutorialRequest){
        ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.CREATED);
        if (tutorialRequest != null)
            tutorialService.saveTutorial(tutorialRequest);
        else
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TutorialResponseDTO> updateTutorial(@PathVariable(value = "id") Long id, @RequestBody TutorialRequestDTO tutorialRequest){
        ResponseEntity<TutorialResponseDTO> response;
        TutorialResponseDTO tutorialResponse;
        if (tutorialRequest != null) {
            tutorialResponse = tutorialService.updateTutorial(id, tutorialRequest);
            response = new ResponseEntity<>(tutorialResponse, HttpStatus.CREATED);
        }else {
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable(value = "id") Long id){
        ResponseEntity<HttpStatus> response;
        if (id != null){
            tutorialService.deleteTutorial(id);
            response = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    //READ
    @GetMapping("/published")
    public ResponseEntity<List<TutorialResponseDTO>> getPublishedTutorials(@RequestParam(value = "is_published") Boolean isPublished){
         return new ResponseEntity<>(tutorialService.findByPublished(isPublished), HttpStatus.OK);
    }
}
