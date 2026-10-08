package com.sam.chat.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.chat.models.Mensagem;

@Repository 
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

    Set<Mensagem> findByConversaId(Long conversaId);

}
