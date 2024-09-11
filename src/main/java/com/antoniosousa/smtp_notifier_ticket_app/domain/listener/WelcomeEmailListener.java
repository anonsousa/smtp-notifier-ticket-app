package com.antoniosousa.smtp_notifier_ticket_app.domain.listener;

import com.antoniosousa.smtp_notifier_ticket_app.domain.model.UserEntity;
import com.antoniosousa.smtp_notifier_ticket_app.domain.service.EmailService;
import com.antoniosousa.smtp_notifier_ticket_app.domain.service.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WelcomeEmailListener {

    private final EmailService emailService;
    private final UserRepository userRepository;


    public WelcomeEmailListener(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.registered.user}")
    public void welcomeEmailQueueListener(UserEntity user) throws MessagingException {
        try {
            emailService.sendEmail(user);
            userRepository.updateNotifiedStatus(user.getId(), true);

        } catch (MessagingException exception) {

            userRepository.updateNotifiedStatus(user.getId(), false);

        }
    }
}
