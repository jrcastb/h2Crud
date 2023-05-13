package com.example.h2crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TutorialResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean published;
}
