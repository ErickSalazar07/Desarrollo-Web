package puj.veterinaria.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Mascota;

@Repository
public interface RepositorioMascota extends JpaRepository<Mascota, Long> {

  public Optional<List<Mascota>> findByClienteCedula(String cedula);

  public long count();

  @Query("SELECT COUNT(m) FROM Mascota m WHERE m.estadoActivo = TRUE")
  public long cantidadMascotasActivas();

}
