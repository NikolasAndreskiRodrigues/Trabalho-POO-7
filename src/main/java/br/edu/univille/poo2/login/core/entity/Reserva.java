package br.edu.univille.poo2.login.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date dataCheckIn;
    private Date dataCheckOut;
    private int quantidadeHospedes;
    private float valorTotal;
    private boolean active;

    @ManyToOne
    private Hotel hotel;

}
