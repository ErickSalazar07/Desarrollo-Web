package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Mascota;

@Repository
public interface RepositorioMascota extends JpaRepository<Mascota, Long> {
  long count();
}
