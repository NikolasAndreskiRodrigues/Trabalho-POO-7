package br.edu.univille.poo2.login.core.service;
import br.edu.univille.poo2.login.core.entity.Reserva;
import br.edu.univille.poo2.login.core.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PolCanc {
    @Autowired
    private ReservaRepository reservaRepository;

    public boolean podeCancelar(Long reservaId) {
        // Obtém a reserva pelo ID
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));

        // Calcula o limite para cancelamento
        LocalDate limiteCancelamento = LocalDate.now().plusDays(7);

        // Verifica se a data de check-in permite cancelamento
        return reserva.getDataCheckIn().isAfter(limiteCancelamento);
    }

    public List<Reserva> listarReservasCancelaveis() {
        // Calcula o limite para cancelamento
        LocalDate limiteCancelamento = LocalDate.now().plusDays(7);

        // Busca reservas que podem ser canceladas
        return reservaRepository.findByDataCheckInAfter(limiteCancelamento);
    }
}