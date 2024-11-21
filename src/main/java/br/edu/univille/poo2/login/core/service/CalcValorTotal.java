package br.edu.univille.poo2.login.core.service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalcValorTotal {

    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private double precoPorNoite = 200.00;  // Preço base por noite (para até 2 pessoas)
    private int numeroDePessoas;  // Número de pessoas na reserva
    private static final int MIN_NIGHTS = 1; // Mínimo de noites para a reserva

    public CalcValorTotal(LocalDate dataCheckIn, LocalDate dataCheckOut, int numeroDePessoas) {
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.numeroDePessoas = numeroDePessoas;
    }

    public boolean verificarDisponibilidade() {
        long numNoites = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        return numNoites >= MIN_NIGHTS;
    }

    public double calcularValorTotal() {
        long numNoites = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        double valorTotal = numNoites * precoPorNoite * numeroDePessoas;
        return valorTotal;
    }
}
