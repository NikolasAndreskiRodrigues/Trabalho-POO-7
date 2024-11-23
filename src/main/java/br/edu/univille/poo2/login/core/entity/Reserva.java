package br.edu.univille.poo2.login.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private int quantidadeHospedes;
    private double valorPorNoite;
    private boolean active;
    private int quantidadePessoas;
    
    @ManyToOne
    private Hotel hotel;

}