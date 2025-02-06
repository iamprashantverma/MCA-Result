package com.college.mca.result.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "result")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schNo;

    private String name;

    private String sem;

    private Long sgpa;

    private Long cgpa;


}
