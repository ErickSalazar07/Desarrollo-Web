package puj.veterinaria.servicios;

import java.util.Collection;

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
  public Mascota searchById(Long id) {
    try {
      return repositorioMascota.findById(id).get();
    } catch(Exception e) {
      return null;
    }
  }

  @Override
  public Collection<Mascota> searchAll() {
    return repositorioMascota.findAll();
  }

  @Override
  public void deleteById(Long id) {
    repositorioMascota.deleteById(id);
  }

  @Transactional
  @Override
  public Mascota updateMascota(Long id,Mascota nuevaMascota) {
    Mascota mascota = repositorioMascota.findById(id).get();

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
  public void addMascota(Mascota mascota) {
    repositorioMascota.save(mascota);
  }

  @Override
  public Long numeroMascotas() {
    return repositorioMascota.count();
  }

  @Override
  public void cambiarEstadoById(Long id) {
    Mascota mascota = repositorioMascota.findById(id).get();

    if(mascota == null) return;

    mascota.setEstadoActivo(!mascota.getEstadoActivo());
    repositorioMascota.save(mascota);
  }
}
