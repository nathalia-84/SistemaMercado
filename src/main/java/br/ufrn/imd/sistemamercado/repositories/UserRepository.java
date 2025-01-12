package br.ufrn.imd.sistemamercado.repositories;

import br.ufrn.imd.sistemamercado.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByLogin(String login);
}
