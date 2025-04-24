package puj.veterinaria.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Tratamiento;

@Repository
public interface RepositorioTratamiento extends JpaRepository<Tratamiento, Long> {

  public Optional<List<Tratamiento>> findByMascotaId(Long id);
  
  public Optional<List<Tratamiento>> findByVeterinarioEncargado_Cedula(String cedula);

  public long countByFechaAfter(LocalDate fecha);

  @Query("SELECT COUNT(t) FROM Tratamiento t WHERE t.drogaAsignada.nombre = ?1")
  public long cantidadTratamientosPorTipoMedicamento(String droga);

  @Query("SELECT t FROM Tratamiento t ORDER BY t.drogaAsignada.unidadVendida DESC")
  public Optional<List<Tratamiento>> top3TratamientosMasUnidadesVendidas(org.springframework.data.domain.Pageable pageable);

}