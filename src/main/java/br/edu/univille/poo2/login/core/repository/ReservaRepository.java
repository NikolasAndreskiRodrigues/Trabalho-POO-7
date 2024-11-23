/*
package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    List<Reserva> findByDataCheckInAfterAndDataCheckOutBefore(LocalDate dataCheckIn, LocalDate dataCheckOut);
    List<Reserva> findByDataCheckInAfter(LocalDate limiteCancelamento);
    List<Reserva> findByDataCheckInGreaterThanEqualAndDataCheckOutLessThanEqual(LocalDate dataCheckIn, LocalDate dataCheckOut);
    List<Reserva> findReservasByUserAndPais(Long userId, Long paisId);
    List<Object[]> findReservasPorHotel(@Param("userId") Long userId);
    List<Reserva> findReservasByUserAndData(Long userId, LocalDate dataCheckIn, LocalDate dataCheckOut);
}
*/