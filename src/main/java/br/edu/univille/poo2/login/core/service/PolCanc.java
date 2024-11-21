package br.edu.univille.poo2.login.core.service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PolCanc {

    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private static final int MIN_NIGHTS = 1;
    private static final int DIAS_PARA_CANCELAMENTO_GRATIS = 7; // 7 dias para cancelamento sem taxa
    private double precoPorNoite = 200.00;

    public double calcularValorTotal() {
        long numNoites = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        double valorTotal = numNoites * precoPorNoite;

        return valorTotal;
    }

    public boolean verificarPoliticaCancelamento(LocalDate dataCancelamento) {
        long diasAntesCheckIn = ChronoUnit.DAYS.between(dataCancelamento, dataCheckIn);
        if (diasAntesCheckIn < DIAS_PARA_CANCELAMENTO_GRATIS) {
            System.out.println("Cancelamento fora do prazo. Cobrança de 1 noite.");
            return false;
        }
        return true;
    }
}
