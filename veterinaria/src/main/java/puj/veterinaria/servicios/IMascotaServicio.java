package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Mascota;

public interface IMascotaServicio {
  public Mascota findById(Long id);
  public List<Mascota> findAll();
  public List<Mascota> findByClienteCedula(String cedula);
  public void deleteById(Long id);
  public void updateMascota(Long id, Mascota mascota);
  public void updateMascota(Mascota mascota);
  public void addMascota(Mascota mascotas);
  public Long numeroMascotas();
  public void cambiarEstadoById(Long id);
  public Long cantidadMascotasActivas();
}
