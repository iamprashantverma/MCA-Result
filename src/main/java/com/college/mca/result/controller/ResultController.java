package com.college.mca.result.controller;

import com.college.mca.result.dto.ResultDTO;
import com.college.mca.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    /* fetching the data from the RestClient */
    @GetMapping("/fetch")
    public ResponseEntity<String> fetchJuniorResult() {
       String results = resultService.fetchJuniorsResult();
       return  ResponseEntity.ok(results);
    }

    /* getting the data from the DB*/
    @GetMapping
    public ResponseEntity<List<ResultDTO>> getResult() {
        List<ResultDTO> result = resultService.getResult();
        return ResponseEntity.ok(result);
    }

}
