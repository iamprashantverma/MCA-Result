package com.college.mca.result.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "result")
@Data
public class ResultEntity {

    @Id
    private String schNo;

    private String name;

    private Double cgpa;


}
