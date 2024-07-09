package com.example.AnalysingH2DataBase.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Patient")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
