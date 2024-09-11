package com.antoniosousa.smtp_notifier_ticket_app.domain.model;

import lombok.Data;

@Data
public class UserEntity {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String createdAt;
    private boolean integrated;
    private boolean notified;
}
