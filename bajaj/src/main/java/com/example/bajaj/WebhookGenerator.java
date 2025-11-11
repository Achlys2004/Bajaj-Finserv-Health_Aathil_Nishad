package com.example.bajaj;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebhookGenerator {

    public static class WebhookResponse {
        private String webhook;
        private String accessToken;

        public WebhookResponse(String webhook, String accessToken) {
            this.webhook = webhook;
            this.accessToken = accessToken;
        }

        public String getWebhook() {
            return webhook;
        }

        public String getAccessToken() {
            return accessToken;
        }
    }

    public static WebhookResponse generateWebhook() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"name\": \"Aathil Nishad\", \"regNo\": \"PES2UG22CS011\", \"email\": \"aathilnishad@gmail.com\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(generateUrl, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        System.out.println("Webhook Generation Response: " + responseBody);

        JsonNode root = mapper.readTree(responseBody);
        String webhook = root.get("webhook").asText();
        String accessToken = root.get("accessToken").asText();

        return new WebhookResponse(webhook, accessToken);
    }
}
