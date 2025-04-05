package puj.veterinaria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  public void addTratamiento(Tratamiento tratamiento) {
    repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void updateTratamiento(Long id, Tratamiento tratamiento) {
    Tratamiento tratamientoActualizar = repositorioTratamiento.findById(id).orElse(null);
    if(tratamientoActualizar == null) return;

    tratamientoActualizar.setNombreTratamiento(tratamiento.getNombreTratamiento());
    tratamientoActualizar.setVeterinarioEncargado(repositorioVeterinario.
    findByCedula(tratamiento.getVeterinarioEncargado().getCedula()));
    tratamientoActualizar.setMascota(repositorioMascota.
    findById(tratamiento.getMascota().getId()).orElse(null));
    tratamientoActualizar.setFecha(tratamiento.getFecha());
    repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void updateTratamiento(Tratamiento tratamiento) {
    repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void deleteById(Long id) {
    repositorioTratamiento.deleteById(id);
  }
}
