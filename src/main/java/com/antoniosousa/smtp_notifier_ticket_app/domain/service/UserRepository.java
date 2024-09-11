package com.antoniosousa.smtp_notifier_ticket_app.domain.service;

import com.antoniosousa.smtp_notifier_ticket_app.domain.model.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void updateNotifiedStatus(Long id, boolean status) {
        String sql = "UPDATE tb_users SET notified = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    public List<UserEntity> findAllUsersWithNotifiedFalse() {
        String sql = "SELECT * FROM tb_users WHERE notified = false";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<UserEntity> userRowMapper() {
        return (rs, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setPassword(rs.getString("password"));
            user.setCreatedAt(String.valueOf(rs.getObject("created_at", OffsetDateTime.class)));
            user.setIntegrated(rs.getBoolean("integrated"));
            user.setNotified(rs.getBoolean("notified"));

            return user;
        };

    }
}
