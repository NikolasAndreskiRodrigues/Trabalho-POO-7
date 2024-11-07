package br.edu.univille.poo2.login.core.repository;

import br.edu.univille.poo2.login.core.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
