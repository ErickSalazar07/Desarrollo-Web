package puj.veterinaria.servicios.mascota;

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
  public Mascota cambiarEstadoById(Long id);
  public Long cantidadMascotasActivas();
}
