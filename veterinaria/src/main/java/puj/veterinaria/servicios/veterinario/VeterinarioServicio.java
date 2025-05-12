package puj.veterinaria.servicios.veterinario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@Service
public class VeterinarioServicio implements IVeterinarioServicio {

  @Autowired
  RepositorioVeterinario repositorioVeterinario;

  RepositorioTratamiento repositorioTratamiento;

  @Override
  public List<Veterinario> findAll() {
    return repositorioVeterinario.findAll();
  }

  @Override
  public Veterinario findByCedula(String cedula) {
    return repositorioVeterinario.findByCedula(cedula).orElse(null);
  }
  
  @Override
  public Veterinario findById(Long id) {
    return repositorioVeterinario.findById(id).orElse(null);
  }

  @Override
  public Veterinario addVeterinario(Veterinario veterinario) {
    return repositorioVeterinario.save(veterinario);
  }

  @Override
  public Veterinario updateVeterinario(Long id, Veterinario veterinario) {
    return repositorioVeterinario.save(veterinario);
  }

  @Override
  public Veterinario updateVeterinario(Veterinario veterinario) {
    return repositorioVeterinario.save(veterinario);
  }


  @Override
  public void deleteById(Long id) {
    repositorioVeterinario.deleteById(id);
  }

  @Override
  public Long cantidadVeterinariosActivos() {
    return repositorioVeterinario.cantidadVeterinariosActivos();
  }

  @Override
  public Long cantidadVeterinariosInactivos() {
    return repositorioVeterinario.cantidadVeterinariosInactivos();
  }

  @Override
  public void cambiarEstadoVeterinarioById(Long id) {
    Veterinario veterinario = repositorioVeterinario.findById(id).orElse(null);
    if(veterinario == null) return;

    veterinario.setActivo(!veterinario.getActivo());
    repositorioVeterinario.save(veterinario);
  }


}
