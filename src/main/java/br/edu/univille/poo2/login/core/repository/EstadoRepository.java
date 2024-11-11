package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Estado;
import br.edu.univille.poo2.login.core.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
    ;
}
