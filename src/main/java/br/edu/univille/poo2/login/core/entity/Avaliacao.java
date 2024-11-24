package br.edu.univille.poo2.login.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private int nota;

    @ManyToOne
    @JoinColumn(name = "hotel_id")  // Define a chave estrangeira explicitamente
    private Hotel hotel;
}
