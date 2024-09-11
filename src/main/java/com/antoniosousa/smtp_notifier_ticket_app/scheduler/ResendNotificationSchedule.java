package com.antoniosousa.smtp_notifier_ticket_app.scheduler;

import com.antoniosousa.smtp_notifier_ticket_app.domain.service.EmailService;
import com.antoniosousa.smtp_notifier_ticket_app.domain.service.UserRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ResendNotificationSchedule {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public ResendNotificationSchedule(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void resendNotification() {

        log.info("Resend notification to failed users");

        userRepository.findAllUsersWithNotifiedFalse().forEach(user -> {

            try {

                emailService.sendEmail(user);
                userRepository.updateNotifiedStatus(user.getId(), true);

            } catch (MessagingException exception) {

                userRepository.updateNotifiedStatus(user.getId(), false);
            }
        });
    }
}
