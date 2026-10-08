package com.sam.chat.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Conversa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany 
    @JoinTable(
        name = "conversa_usuarios",
        joinColumns = @JoinColumn(name = "conversa_id"),
        inverseJoinColumns = @JoinColumn(name = "usuarios_id")
    )
    Set<Usuario> usuarios;

}
