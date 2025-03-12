package puj.veterinaria.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Droga;
import puj.veterinaria.repositorios.RepositorioDroga;

@Service
public class DrogaServicio implements IDrogaServicio {

  @Autowired
  RepositorioDroga repositorioDroga;

  @Override
  public Collection<Droga> findAll() {
    return repositorioDroga.findAll();
  }

  @Override
  public Droga findById(Long id) {
    return repositorioDroga.findById(id).get();
  }

  @Override
  public void addDroga(Droga droga) {
    repositorioDroga.save(droga);
  }

  @Override
  public void updateDroga(Long id, Droga droga) throws Exception {
    if(repositorioDroga.findById(id).get() == null)
      throw new RuntimeException();
    repositorioDroga.save(droga);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    if(repositorioDroga.findById(id).get() == null)
      throw new RuntimeException();
    repositorioDroga.deleteById(id);
  }
  
}
