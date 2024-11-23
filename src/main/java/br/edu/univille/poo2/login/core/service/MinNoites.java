package br.edu.univille.poo2.login.core.service;
import br.edu.univille.poo2.login.core.entity.Reserva;
import br.edu.univille.poo2.login.core.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MinNoites {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> buscarReservas(LocalDate datacheckIn, LocalDate datacheckOut) {
        if (datacheckOut.isBefore(datacheckIn.plusDays(1))) {
            throw new IllegalArgumentException("A reserva deve ter duração mínima de 1 dia.");
        }
        return reservaRepository.findByDataCheckInAfterAndDataCheckOutBefore(datacheckIn, datacheckOut);
        }
    }

