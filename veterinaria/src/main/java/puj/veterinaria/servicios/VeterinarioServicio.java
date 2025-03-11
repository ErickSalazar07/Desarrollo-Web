package puj.veterinaria.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@Service
public class VeterinarioServicio implements IVeterinarioServicio {

  @Autowired
  RepositorioVeterinario repositorioVeterinario;

  @Override
  public Collection<Veterinario> findAll() {
    return repositorioVeterinario.findAll();
  }

  @Override
  public Veterinario findById(Long id) {
    return repositorioVeterinario.findById(id).get();
  }

  @Override
  public void addVeterinario(Veterinario veterinario) {
    repositorioVeterinario.save(veterinario);
  }

  @Override
  public void updateVeterinario(Long id, Veterinario veterinario) throws Exception {
    if(repositorioVeterinario.findById(id).get() == null) throw new RuntimeException();
    repositorioVeterinario.save(veterinario);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    if(repositorioVeterinario.findById(id).get() == null) throw new RuntimeException();
    repositorioVeterinario.deleteById(id);
  }
  
}
