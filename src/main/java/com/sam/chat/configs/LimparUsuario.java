package com.sam.chat.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LimparUsuario implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public LimparUsuario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {

        jdbcTemplate.update(
            "DELETE FROM USUARIO WHERE ID = 5"
        );

        System.out.println("Usuário duplicado removido!");
    }
}