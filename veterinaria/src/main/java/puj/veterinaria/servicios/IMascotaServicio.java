package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Mascota;

public interface IMascotaServicio {
  public Mascota searchById(Integer id);
  public Collection<Mascota> searchAll();
  public void deleteById(Integer id);
  public void updateMascota(Mascota mascota);
  public void addMascota(Mascota mascotas);
}
