package com.college.mca.result.service;

import com.college.mca.result.dto.ResultDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestClientService {
    private final  String BASE_URL ="http://erpapi.manit.ac.in/api/fetch_register";
    private final String TOKEN ="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6eyJkbiI6InVpZD0yMzIwNDAzMTE0LG91PXN0dWRlbnRzLGRjPW1hbml0LGRjPWFjLGRjPWluIiwiY29udHJvbHMiOltdLCJvYmplY3RDbGFzcyI6WyJpbmV0T3JnUGVyc29uIiwic2hhZG93QWNjb3VudCIsInBvc2l4QWNjb3VudCJdLCJzbiI6IlZFUk1BIiwiZ2l2ZW5OYW1lIjoiUFJBU0hBTlQiLCJvdSI6InN0dWRlbnRzIiwicG9zdGFsQWRkcmVzcyI6IkhvdXNlIG5vLiA1MyIsIm1vYmlsZSI6Ijg0MTk4ODE2MTIiLCJtYWlsIjoidmVybWFwdjc0OUBnbWFpbC5jb20iLCJzaGFkb3dMYXN0Q2hhbmdlIjoiMTk2NjkiLCJnaWROdW1iZXIiOiIxMDAwMCIsImdlY29zIjoiUFJBU0hBTlQgVkVSTUEiLCJ1aWQiOiIyMzIwNDAzMTE0IiwiaG9tZURpcmVjdG9yeSI6Ii9ob21lLzIzMjA0MDMxMTQiLCJsb2dpblNoZWxsIjoiL2Jpbi9iYXNoIiwiY24iOiJQUkFTSEFOVCBWRVJNQSIsInVpZE51bWJlciI6IjI1NDY4IiwidXNlclBhc3N3b3JkIjoie1NTSEF9cG4vdEdKYUVvQ20vR3BuaFUwVDBJUGx1ZlNkdWR5MFMiLCJzdHVkZW50SW5mbyI6W3sic3R1ZGVudHVpZCI6Mzc0NiwiZnVsbF9uYW1lIjoiUFJBU0hBTlQgVkVSTUEiLCJyb2xsX25vIjoiMjMyMDQwMzExNCIsImRvYiI6IjMvOS8yMDAyIiwicGhvbmVfbnVtYmVyIjoiODQxOTg4MTYxMiIsImdlbmRlciI6Ik0iLCJhYWRoYXJfbm8iOiIzMzMzNTMxNDQ5NDQiLCJpbnN0aXR1dGVfZW1haWxfaWQiOiJ2ZXJtYXB2NzQ5QGdtYWlsLmNvbSIsImFjY291bnRzX3BheW1lbnRfdHlwZV9pZCI6MTIsImFiY19pZCI6Ijk5OTE3OTY5Njc5NiIsInN0YXJ0X3Nlc3Npb24iOjIwMjMsInByb2dyYW1fbWFzdGVyX2lkIjoyOCwicHJvZ3JhbV9uYW1lIjoiTWFzdGVyIG9mIENvbXB1dGVyIEFwcGxpY2F0aW9ucyIsInByb2dyYW1fdHlwZV9pZF9jb2RlIjo2MywiY29kZSI6Ik1DQSIsImNvZGVfZGVzYyI6Ik1DQSIsInBheW1lbnRfdHlwZSI6IkdFTi9PQkMvRVdTIChQRy9QaC5EKSIsImhvc3RlbCI6Ikg5LTAyMCJ9XSwic2VtZXN0ZXJfdGVybSI6eyJzdHVkZW50dWlkIjozNzQ2LCJzZW1lc3Rlcl9yZWdfY29tcGxldGlvbl9zdGF0dXMiOiJSIiwic2VtZXN0ZXJfdGVybV9ub19pZF9jb2RlIjo2LCJzdGFydF9zZXNzaW9uIjoyMDIzLCJwcm9ncmFtX21hc3Rlcl9pZCI6MjgsInZlcnNpb24iOjEsImN1cnJpY3VsdW1fc3RhcnRfc2Vzc2lvbiI6MjAyMSwic3RhcnRfc2VtZXN0ZXJfdHlwZV9pZF9jb2RlIjoyLCJzZXNzaW9uIjoyMDI0LCJzY2hlZHVsZV90eXBlX3ZhbHVlIjoiMiIsImNvZGVfZGVzYyI6IlNlbWVzdGVyIDMiLCJyZWdpc3RyYXRpb24iOiJDb25maXJtZWQiLCJkZXB0X2lkIjozMiwicHJvZ3JhbV9uYW1lIjoiTWFzdGVyIG9mIENvbXB1dGVyIEFwcGxpY2F0aW9ucyIsImRlZ3JlZV9sZXZlbF9pZF9jb2RlIjo0NSwicHJvZ3JhbV90eXBlX2lkX2NvZGUiOjYzfSwic3ViamVjdHMiOltdLCJwcm9ncmFtIjpbeyJwcm9ncmFtX21hc3Rlcl9pZCI6MjgsInByb2dyYW1fbmFtZSI6Ik1hc3RlciBvZiBDb21wdXRlciBBcHBsaWNhdGlvbnMiLCJzdGFydF9zZXNzaW9uIjoyMDIxLCJkZWdyZWVfbGV2ZWxfaWRfY29kZSI6NDUsInByb2dyYW1fdHlwZV9pZF9jb2RlIjo2M31dfSwiaWF0IjoxNzM4Njk0MzEzLCJleHAiOjE3NDczMzQzMTN9.OHANtfHYeLkdWpXqROzJxkjllnkJpnmY1JHpELQ2RVM";
    private final RestClient restClient;
    private final String requestBody = "{\"studentuid\":7609}";
    ObjectMapper objectMapper = new ObjectMapper();

    public List<ResultDTO> fetchJuniorsResult() {

        List<ResultDTO> resultList = new ArrayList<>();
        for (int studentId = 7604; studentId <= 7720; studentId++) {
            try {
                // Creating dynamic request body
                String requestBody = "{\"studentuid\": " + studentId + "}";

                // Making API call
                String jsonResp = restClient.post()
                        .uri(BASE_URL)
                        .body(requestBody)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json")
                        .retrieve()
                        .body(String.class);

                // Parsing JSON response
                JsonNode rootNode = objectMapper.readTree(jsonResp);
                if (rootNode.isArray() && !rootNode.isEmpty()) {
                    JsonNode firstEntry = rootNode.get(0);
                    String fullName = firstEntry.get("full_name").asText();
                    String rollNo = firstEntry.get("roll_no").asText();
                    String cgpa = firstEntry.get("sgpa_cgpa_in_json").get(0).get("cgpa").asText();

                    // Creating DTO and adding to list
                    ResultDTO result = new ResultDTO();
                    result.setName(fullName) ;
                    result.setCgpa(Double.valueOf(cgpa)) ;
                    result.setSchNo(rollNo);
                    resultList.add(result);
                }

            } catch (Exception ex) {
                log.error("Error fetching results for Student ID,{} , : ,{} " , studentId, ex.getMessage());
            }
        }
        return resultList;
    }

}
