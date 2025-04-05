package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Tratamiento;

public interface ITratamientoServicio {
  public List<Tratamiento> findAll();
  public Tratamiento findById(Long id);
  public void addTratamiento(Tratamiento tratamiento);
  public void updateTratamiento(Long id, Tratamiento tratamiento);
  public void updateTratamiento(Tratamiento tratamiento);
  public void deleteById(Long id);
}
