package com.sam.chat.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.chat.models.Conversa;
import com.sam.chat.models.Mensagem;
import com.sam.chat.models.Usuario;
import com.sam.chat.repositories.ConversaRepository;

@Service 
public class ConversaService {

    @Autowired 
    ConversaRepository conversaRepository;

    @Autowired 
    UsuarioService usuarioService;

    public Conversa getOrCreateConversa(Long conversaId, Long usuarioChamadoId){
        
        return conversaRepository.findById(conversaId)
                    .orElseGet(() -> {

                        Conversa novaConversa = new Conversa(
                                null,
                                geraUsuariosDaConversa(usuarioChamadoId)
                        );

                        return conversaRepository.save(novaConversa);
                    });
                    
    }

    
    public List<Conversa> findByNomeUsuario(String nome){
        return conversaRepository.findByUsuariosNomeContainingIgnoreCase(nome);
    }

    public Optional<Conversa> findByUsuariosId(Long id){
        return conversaRepository.findByUsuariosId(id);
    }

    public Set<Usuario> geraUsuariosDaConversa(Long usuarioChamadoId){
        return Set.of(
        usuarioService.getUsuarioAtual(),
        usuarioService.findById(usuarioChamadoId)
        );
    }

}
