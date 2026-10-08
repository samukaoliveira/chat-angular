package com.sam.chat.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.chat.configs.AuthRequest;
import com.sam.chat.configs.AuthResponse;
import com.sam.chat.configs.JwtService;

@RestController 
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    
    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody AuthRequest request){
        
    //     Authentication auth = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(
    //             request.email(), 
    //             request.senha())
    //     );

    //     String token = jwtService.generateToken(request.email());

    //     return ResponseEntity.ok(new AuthResponse(token));
    // }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        System.out.println("========== LOGIN ==========");
        System.out.println("EMAIL: " + request.email());
        System.out.println("SENHA: " + request.senha());

        try {

            Authentication authentication =
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                    )
                );

            System.out.println("========== AUTENTICADO ==========");
            System.out.println("USUÁRIO: " + authentication.getName());

            String token = jwtService.generateToken(request.email());

            System.out.println("========== TOKEN GERADO ==========");

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (Exception e) {

            System.out.println("========== ERRO NO LOGIN ==========");
            System.out.println("TIPO: " + e.getClass().getName());
            System.out.println("MENSAGEM: " + e.getMessage());

            e.printStackTrace();

            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
