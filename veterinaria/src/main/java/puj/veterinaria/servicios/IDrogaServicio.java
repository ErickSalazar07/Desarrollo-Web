package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Droga;

public interface IDrogaServicio {
  public List<Droga> findAll();
  public Droga findById(Long id);
  public void addDroga(Droga droga);
  public void updateDroga(Long id, Droga droga);
  public void updateDroga(Droga droga);
  public void deleteById(Long id);
  public Long numDrogas();
  public Double totalVentas();
  public Double totalGanancias();
}
