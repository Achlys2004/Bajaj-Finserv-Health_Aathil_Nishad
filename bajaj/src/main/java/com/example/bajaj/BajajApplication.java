package com.example.bajaj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajajApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BajajApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WebhookGenerator.WebhookResponse webhookResponse = WebhookGenerator.generateWebhook();
		SolutionSubmitter.submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken());
	}
}
