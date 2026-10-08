package com.sam.chat.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.chat.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);

}
