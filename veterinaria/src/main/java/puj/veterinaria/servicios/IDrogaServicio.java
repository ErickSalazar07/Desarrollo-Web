package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Droga;

public interface IDrogaServicio {
  public Collection<Droga> findAll();
  public Droga findById(Long id);
  public void addDroga(Droga droga);
  public void updateDroga(Long id, Droga droga) throws Exception;
  public void deleteById(Long id) throws Exception;
}
