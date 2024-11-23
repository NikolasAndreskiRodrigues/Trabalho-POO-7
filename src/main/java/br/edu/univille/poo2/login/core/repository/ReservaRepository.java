package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    List<Reserva> findBydataCheckInAfterAnddataCheckOutBefore(LocalDate dataCheckIn, LocalDate datacheckOut);
}
