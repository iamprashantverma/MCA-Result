package com.college.mca.result.service;

import com.college.mca.result.dto.ResultDTO;
import com.college.mca.result.entities.ResultEntity;
import com.college.mca.result.repositories.ResultRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final RestClientService restClientService;
    private final ModelMapper modelMapper;
    private final ResultRepo resultRepo;

    /* fetched that from restClientServer and Persist into DB */
    @Transactional
    public String fetchJuniorsResult() {
        List<ResultDTO> data =  restClientService.fetchJuniorsResult();
        for (ResultDTO res : data) {
            ResultEntity result = modelMapper.map(res,ResultEntity.class);
            result.setSchNo(res.getSchNo());
            resultRepo.save(result);
        }
        return  "data fetched successfully";
    }

    /* fetching the data from db and return to the client */
    public List<ResultDTO> getResult() {
        List<ResultEntity> res = resultRepo.findAll();
        return  res.stream()
                .map(data->modelMapper.map(data,ResultDTO.class))
                .toList();
    }


}
