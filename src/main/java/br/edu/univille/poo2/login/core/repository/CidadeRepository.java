package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.Cidade;
import br.edu.univille.poo2.login.core.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    ;
}
