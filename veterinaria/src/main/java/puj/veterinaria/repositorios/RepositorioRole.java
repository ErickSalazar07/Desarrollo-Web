package puj.veterinaria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import puj.veterinaria.entidades.Role;

public interface RepositorioRole extends JpaRepository<Role,Long> {

  Optional<Role> findByName(String name);
}