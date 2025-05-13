package puj.veterinaria.servicios.mascota;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;

@Service
public class MascotaServicio implements IMascotaServicio {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Autowired
  RepositorioCliente repositorioCliente;

  @Override
  public Mascota findById(Long id) {
    return repositorioMascota.findById(id).orElse(null);
  }

  @Override
  public List<Mascota> findAll() {
    return repositorioMascota.findAll();
  }

  @Override
  public List<Mascota> findByClienteCedula(String cedula) {
    return repositorioMascota.findByClienteCedula(cedula).orElse(null);
  }

  @Override
  public void deleteById(Long id) {
    repositorioMascota.deleteById(id);
  }

  @Transactional
  @Override
  public Mascota updateMascota(Long id,Mascota nuevaMascota) {
    Mascota mascota = repositorioMascota.findById(id).orElse(null);

    if(mascota == null) return null;

    mascota.setNombre(nuevaMascota.getNombre());
    mascota.setRaza(nuevaMascota.getRaza());
    mascota.setEdad(nuevaMascota.getEdad());
    mascota.setPeso(nuevaMascota.getPeso());
    mascota.setEnfermedad(nuevaMascota.getEnfermedad());
    mascota.setFoto(nuevaMascota.getFoto());
    mascota.setCliente(repositorioCliente.findByCedula(nuevaMascota.getCliente().getCedula()));
    mascota.setEstadoActivo(nuevaMascota.getEstadoActivo());

    return repositorioMascota.save(mascota);
  }

  @Override
  public Mascota updateMascota(Mascota mascota) {
    return repositorioMascota.save(mascota);
  }

  @Override
  public Mascota addMascota(Mascota mascota) {
    return repositorioMascota.save(mascota);
  }

  @Override
  public Long numeroMascotas() {
    return repositorioMascota.count();
  }

  @Override
  public Mascota cambiarEstadoById(Long id) {
    Mascota mascota = repositorioMascota.findById(id).orElse(null);
    if(mascota == null) return null;

    mascota.setEstadoActivo(!mascota.getEstadoActivo());
    return repositorioMascota.save(mascota);
  }

  @Override
  public Long cantidadMascotasActivas() {
    return repositorioMascota.cantidadMascotasActivas();
  }
}
