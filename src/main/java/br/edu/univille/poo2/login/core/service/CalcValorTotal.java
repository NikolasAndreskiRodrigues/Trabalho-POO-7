package br.edu.univille.poo2.login.core.service;
import br.edu.univille.poo2.login.core.entity.Reserva;
import br.edu.univille.poo2.login.core.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalcValorTotal {

    @Autowired
    private ReservaRepository reservaRepository;

    public double calcularValorTotal(Long reservaId) {
        // Busca a reserva pelo ID
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));

        // Calcula a quantidade de dias
        long quantidadeDias = java.time.temporal.ChronoUnit.DAYS.between(
                reserva.getDataCheckIn(), reserva.getDataCheckOut());

        if (quantidadeDias <= 0) {
            throw new IllegalArgumentException("A reserva deve ter pelo menos 1 dia.");
        }

        // Calcula o valor total
        double valorTotal = quantidadeDias * reserva.getValorPorNoite() * reserva.getQuantidadePessoas();

        return valorTotal;
    }
}