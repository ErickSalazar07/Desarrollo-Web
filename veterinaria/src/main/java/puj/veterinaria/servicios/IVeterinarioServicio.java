package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Veterinario;

public interface IVeterinarioServicio {
  public List<Veterinario> findAll();
  public Veterinario findById(Long id);
  public Veterinario findByCedula(String cedula);
  public Veterinario addVeterinario(Veterinario veterinario);
  public Veterinario updateVeterinario(Long id, Veterinario veterinario);
  public Veterinario updateVeterinario(Veterinario veterinario);
  public void deleteById(Long id);
  public Long cantidadVeterinariosActivos();
  public Long cantidadVeterinariosInactivos();
  public void cambiarEstadoVeterinarioById(Long id);
}
