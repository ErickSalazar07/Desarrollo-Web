package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Tratamiento;

public interface ITratamientoServicio {
  public Collection<Tratamiento> findAll();
  public Tratamiento findById(Long id);
  public void addTratamiento(Tratamiento tratamiento);
  public void updateTratamiento(Long id, Tratamiento tratamiento) throws Exception;
  public void deleteById(Long id) throws Exception;
}
