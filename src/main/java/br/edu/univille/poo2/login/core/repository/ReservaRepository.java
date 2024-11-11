package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Reserva;
import br.edu.univille.poo2.login.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    ;
}
