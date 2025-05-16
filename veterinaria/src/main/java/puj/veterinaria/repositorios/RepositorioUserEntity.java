package puj.veterinaria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import puj.veterinaria.entidades.UserEntity;

public interface RepositorioUserEntity extends JpaRepository<UserEntity,Long> {
  
  Optional<UserEntity> findByUsername(String username);
  Boolean existsByUsername(String username);
}
