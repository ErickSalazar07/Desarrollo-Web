package puj.veterinaria.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.repositorios.RepositorioMascota;

@Service
public class MascotaServicio implements IMascotaServicio {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Override
  public Mascota searchById(Integer id) {
    return repositorioMascota.findById(id);
  }

  @Override
  public Collection<Mascota> searchAll() {
    return repositorioMascota.findAll();
  }
}
