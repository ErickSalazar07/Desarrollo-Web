package puj.veterinaria.servicios.droga;

import java.util.List;

import puj.veterinaria.entidades.Droga;

public interface IDrogaServicio {
  public List<Droga> findAll();
  public Droga findById(Long id);
  public Droga addDroga(Droga droga);
  public Droga updateDroga(Long id, Droga droga);
  public Droga updateDroga(Droga droga);
  public void deleteById(Long id);
  public Long numDrogas();
  public Double totalVentas();
  public Double totalGanancias();
}
