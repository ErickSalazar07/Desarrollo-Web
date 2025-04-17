package puj.veterinaria.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Tratamiento;

@Repository
public interface RepositorioTratamiento extends JpaRepository<Tratamiento, Long> {
  public Optional<List<Tratamiento>> findByMascotaId(Long id);
  public long count();

  @Query(
  value = "SELECT COUNT(*) FROM tratamiento t WHERE t.fecha >= CURRENT_DATE - INTERVAL '1 month'", 
  nativeQuery = true)
  public long cantidadTratamientosUltimoMes();
}