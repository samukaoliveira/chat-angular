package com.sam.chat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sam.chat.models.Usuario;
import com.sam.chat.repositories.UsuarioRepository;

@Service 
public class UsuarioService {

    @Autowired 
    UsuarioRepository usuarioRepository;

    @Autowired 
    PasswordEncoder passwordEncoder;

    public Optional<Usuario> findByNome(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario save(Usuario usuario){
        usuario.setSenha(
            passwordEncoder.encode(usuario.getSenha())
        );
        
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioAtual(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario getUsuarioAtual(){
        
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return getUsuarioAtual(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
