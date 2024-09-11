package com.antoniosousa.smtp_notifier_ticket_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmtpNotifierTicketAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmtpNotifierTicketAppApplication.class, args);
	}

}
