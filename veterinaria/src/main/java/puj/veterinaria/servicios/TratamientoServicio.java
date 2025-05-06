package puj.veterinaria.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.repositorios.RepositorioMascota;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@Service
public class TratamientoServicio implements ITratamientoServicio {

  @Autowired
  RepositorioTratamiento repositorioTratamiento;

  @Autowired
  RepositorioVeterinario repositorioVeterinario;

  @Autowired
  RepositorioMascota repositorioMascota;

  @Override
  public List<Tratamiento> findAll() {
    return repositorioTratamiento.findAll();
  }

  @Override
  public Tratamiento findById(Long id) {
    return repositorioTratamiento.findById(id).orElse(null);
  }

  @Override
  public List<Tratamiento> tratamientosVeterinario(String cedula){
    return repositorioTratamiento.findByVeterinarioEncargado_Cedula(cedula).orElse(null);
  }

  @Override
  public List<Tratamiento> findByMascotaId(Long id) {
    return repositorioTratamiento.findByMascotaId(id).orElse(null);
  }

  @Override
  public Tratamiento addTratamiento(Tratamiento tratamiento) {
    return repositorioTratamiento.save(tratamiento);
  }

  @Override
  public Tratamiento updateTratamiento(Long id, Tratamiento tratamiento) {
    Tratamiento tratamientoActualizar = repositorioTratamiento.findById(id).orElse(null);
    if(tratamientoActualizar == null) return null;

    tratamientoActualizar.setNombreTratamiento(tratamiento.getNombreTratamiento());
    tratamientoActualizar.setVeterinarioEncargado(repositorioVeterinario.
    findByCedula(tratamiento.getVeterinarioEncargado().getCedula()).orElse(null));
    tratamientoActualizar.setMascota(repositorioMascota.
    findById(tratamiento.getMascota().getId()).orElse(null));
    tratamientoActualizar.setFecha(tratamiento.getFecha());
    return repositorioTratamiento.save(tratamiento);
  }

  @Override
  public Tratamiento updateTratamiento(Tratamiento tratamiento) {
    return repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void deleteById(Long id) {
    repositorioTratamiento.deleteById(id);
  }

  @Override
  public Long cantidadTratamientosUltimoMes() {
    return repositorioTratamiento.countByFechaAfter(LocalDate.now().minusDays(30));
  }

  @Override
  public Long cantidadTratamientosTipoMedicamento(String medicamento) {
    return repositorioTratamiento.cantidadTratamientosPorTipoMedicamento(medicamento);
  }

  @Override
  public List<Tratamiento> top3TratamientosMasUnidadesVendidas() {
    return repositorioTratamiento.top3TratamientosMasUnidadesVendidas(PageRequest.of(0,3)).orElse(null);
  }

  @Override
  public List<Tratamiento> findByVeterinarioEncargado_Cedula(String cedula) {
    return repositorioTratamiento.findByVeterinarioEncargado_Cedula(cedula).orElse(null);
  }
}
