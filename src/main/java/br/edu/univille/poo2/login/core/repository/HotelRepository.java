package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Hotel;
import br.edu.univille.poo2.login.core.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
   ;
}
