package com.college.mca.result.controller;

import com.college.mca.result.dto.ResultDTO;
import com.college.mca.result.service.RestClientService;
import com.college.mca.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private final RestClientService restClientService;

//    @GetMapping("/junior")
//    public ResponseEntity<List<ResultDTO>> getJuniorResult() {
//       List<ResultDTO> results = resultService.getJuniorsResult();
//       return  ResponseEntity.ok(results);
//    }

    @GetMapping
    public void hello() {

            restClientService.fetchJuniorsResult();
    }

}
