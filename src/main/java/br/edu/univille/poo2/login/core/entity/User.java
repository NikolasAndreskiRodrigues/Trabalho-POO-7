package br.edu.univille.poo2.login.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Garante que o username seja único e não nulo
    private String username;

    private String name;

    @Column(nullable = false) // Garante que a senha não seja nula
    private String password;

    @Column(unique = true, nullable = false) // Garante que o email seja único e não nulo
    private String email;

    private boolean active;

    @OneToOne
    private UserRole role;
}
