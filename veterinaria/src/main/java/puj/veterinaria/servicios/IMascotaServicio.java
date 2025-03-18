package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Mascota;

public interface IMascotaServicio {
  public Mascota searchById(Long id);
  public Collection<Mascota> searchAll();
  public void deleteById(Long id);
  public Mascota updateMascota(Long id, Mascota mascota);
  public void addMascota(Mascota mascotas);
  public Long numeroMascotas();
  public void cambiarEstadoById(Long id);
}
