package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Veterinario;

public interface IVeterinarioServicio {
  public Collection<Veterinario> findAll();
  public Veterinario findById(Long id);
  public void addVeterinario(Veterinario veterinario);
  public void updateVeterinario(Long id, Veterinario veterinario) throws Exception;
  public void deleteById(Long id) throws Exception;
}
