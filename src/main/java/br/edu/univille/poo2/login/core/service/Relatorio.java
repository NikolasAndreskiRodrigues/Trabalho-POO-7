package br.edu.univille.poo2.login.core.service;
import br.edu.univille.poo2.login.core.entity.Reserva;
import br.edu.univille.poo2.login.core.repository.PaisRepository;
import br.edu.univille.poo2.login.core.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Relatorio {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PaisRepository paisRepository;

    // Relatório por País
    public List<Reserva> gerarRelatorioPorPais(Long userId, Long paisId) {
        // Verifica se o país existe no banco
        paisRepository.findById(paisId)
                .orElseThrow(() -> new IllegalArgumentException("País não encontrado."));

        // Busca reservas por usuário e país
        return reservaRepository.findReservasByUserAndPais(userId, paisId);
    }

    // Relatório por Data
    public List<Reserva> gerarRelatorioPorData(Long userId, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        if (dataCheckIn.isAfter(dataCheckOut)) {
            throw new IllegalArgumentException("A data inicial não pode ser depois da data final.");
        }

        return reservaRepository.findReservasByUserAndData(userId, dataCheckIn, dataCheckOut);
    }

    // Relatório por vezes reservadas em cada hotel
    public Map<String, Long> gerarRelatorioPorHotel(Long userId) {
        List<Object[]> resultados = reservaRepository.findReservasPorHotel(userId);

        // Converte a lista de objetos para um mapa: hotel -> quantidade de vezes
        Map<String, Long> relatorio = new HashMap<>();
        for (Object[] resultado : resultados) {
            String nameHotel = (String) resultado[0];
            Long quantidade = (Long) resultado[1];
            relatorio.put(nameHotel, quantidade);
        }
        return relatorio;
    }
}
