package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Veterinario;

@Repository
public interface RepositorioVeterinario extends JpaRepository<Veterinario, Long> {
  public Veterinario findByCedula(String cedula);
  public long count();
}
