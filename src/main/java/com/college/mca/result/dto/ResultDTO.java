package com.college.mca.result.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ResultDTO {
    private String schNo;
    private String name;
    private Double cgpa;
}
