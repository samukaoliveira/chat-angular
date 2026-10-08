package com.sam.chat.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.chat.models.Conversa;

@Repository 
public interface ConversaRepository extends JpaRepository<Conversa, Long>{

    List<Conversa> findByUsuariosNomeContainingIgnoreCase(String nome);

    Optional<Conversa> findByUsuariosId(Long id);

}
