package com.sam.chat.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.chat.models.Mensagem;
import com.sam.chat.models.Usuario;
import com.sam.chat.services.MensagemService;

@RestController 
@RequestMapping("/conversas")
public class MensagemController {

    @Autowired 
    MensagemService mensagemService;
    
    @PostMapping("/{conversaId}/{usuarioChamadoId}/enviar")
    public void enviar(@RequestBody Mensagem mensagem, @PathVariable Long conversaId, Long usuarioChamadoId){
        mensagemService.enviar(mensagem, conversaId, usuarioChamadoId);
    }

    @PostMapping("/")
    public List<Usuario> conversas(@RequestBody String nomeUsuario){
        return mensagemService.pesquisaUsuariosDeConversas(nomeUsuario);

    }

    @GetMapping("/{conversaId}")
    public Set<Mensagem> conversa(@PathVariable("conversaId") Long conversaId){
        return mensagemService.carregarConversaPorId(conversaId);

    }

}
