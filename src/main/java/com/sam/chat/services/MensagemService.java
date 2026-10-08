package com.sam.chat.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.chat.models.Conversa;
import com.sam.chat.models.Mensagem;
import com.sam.chat.models.Usuario;
import com.sam.chat.repositories.ConversaRepository;
import com.sam.chat.repositories.MensagemRepository;
import com.sam.chat.repositories.UsuarioRepository;

@Service 
public class MensagemService {

    @Autowired 
    MensagemRepository mensagemRepository;

    @Autowired 
    ConversaService conversaService;

    @Autowired 
    UsuarioService usuarioService;

    public Mensagem enviar(Mensagem mensagem, Long conversaId, Long usuarioChamadoId) {

        Conversa conversa = conversaService.getOrCreateConversa(conversaId, usuarioChamadoId);

        mensagem.setConversa(conversa);
        mensagem.setHoraEnvio(LocalDateTime.now());
        System.out.println("DEPOIS DO HORARIO: " + mensagem.getHoraEnvio());

        return mensagemRepository.save(mensagem);
    }

    public Set<Mensagem> carregarConversaPorId(Long conversaId){
        return mensagemRepository.findByConversaId(conversaId);
    }

    public Set<Mensagem> carregarConversaPorUsuario(Long usuarioId){
        Optional<Conversa> conversa = conversaService.findByUsuariosId(usuarioId);

        return mensagemRepository.findByConversaId(conversa.get().getId());
    }

    public List<Usuario> pesquisaUsuariosDeConversas(String findByNomeUsuario){
        List<Conversa> conversas = conversaService.findByNomeUsuario(findByNomeUsuario);

        List<Usuario> usuariosEncontrados = new ArrayList<>();

        conversas.forEach(c -> usuariosEncontrados
                        .addAll(c.getUsuarios().stream()
                            .filter(u -> !u.getId().equals(usuarioService.getUsuarioAtual("ciclano@gmail.com").get().getId())).toList()
                    ));

        return usuariosEncontrados;
    }



}
