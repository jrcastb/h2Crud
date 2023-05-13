package com.example.h2crud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutorials")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private Boolean published;
}
