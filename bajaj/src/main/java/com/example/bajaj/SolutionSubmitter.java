package com.example.bajaj;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SolutionSubmitter {

    public static void submitSolution(String webhookUrl, String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        String finalQuery = "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p INNER JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID INNER JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE DAY(p.PAYMENT_TIME) != 1 ORDER BY p.AMOUNT DESC LIMIT 1;";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        String body = "{\"finalQuery\": \"" + finalQuery.replace("\"", "\\\"") + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                entity,
                String.class);

        System.out.println("Submission Response: " + response.getBody());
        System.out.println("Submission Status Code: " + response.getStatusCode());
    }
}
