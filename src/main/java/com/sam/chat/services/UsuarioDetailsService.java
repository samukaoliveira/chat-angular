package com.sam.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sam.chat.models.Usuario;
import com.sam.chat.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado")
            );

        return User
            .withUsername(usuario.getEmail())
            .password(usuario.getSenha())
            .roles("USER")
            .build();
    }
}