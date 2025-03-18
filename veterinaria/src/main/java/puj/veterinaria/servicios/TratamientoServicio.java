package puj.veterinaria.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.repositorios.RepositorioTratamiento;

@Service
public class TratamientoServicio implements ITratamientoServicio {

  @Autowired
  RepositorioTratamiento repositorioTratamiento;

  @Override
  public Collection<Tratamiento> findAll() {
    return repositorioTratamiento.findAll();
  }

  @Override
  public Tratamiento findById(Long id) {
    return repositorioTratamiento.findById(id).get();
  }

  @Override
  public void addTratamiento(Tratamiento tratamiento) {
    repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void updateTratamiento(Long id, Tratamiento tratamiento) throws Exception {
    if(repositorioTratamiento.findById(id).get() == null) throw new RuntimeException();
    repositorioTratamiento.save(tratamiento);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    if(repositorioTratamiento.findById(id).get() == null) throw new RuntimeException();
    repositorioTratamiento.deleteById(id);
  }


}
