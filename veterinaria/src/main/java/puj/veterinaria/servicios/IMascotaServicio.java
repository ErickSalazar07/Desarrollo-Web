package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Mascota;

public interface IMascotaServicio {
  public Mascota findById(Long id);
  public List<Mascota> findAll();
  public List<Mascota> findByClienteCedula(String cedula);
  public void deleteById(Long id);
  public Mascota updateMascota(Long id, Mascota mascota);
  public Mascota updateMascota(Mascota mascota);
  public Mascota addMascota(Mascota mascotas);
  public Long numeroMascotas();
  public void cambiarEstadoById(Long id);
  public Long cantidadMascotasActivas();
}
