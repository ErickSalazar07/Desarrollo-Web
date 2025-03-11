package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Tratamiento;

@Repository
public interface RepositorioTratamiento extends JpaRepository<Tratamiento, Long> {
  
}