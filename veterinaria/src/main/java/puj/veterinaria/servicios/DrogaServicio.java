package puj.veterinaria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Droga;
import puj.veterinaria.repositorios.RepositorioDroga;
import puj.veterinaria.repositorios.RepositorioTratamiento;

@Service
public class DrogaServicio implements IDrogaServicio {

  @Autowired
  RepositorioDroga repositorioDroga;

  @Autowired
  RepositorioTratamiento repositorioTratamiento;

  @Override
  public List<Droga> findAll() {
    return repositorioDroga.findAll();
  }

  @Override
  public Droga findById(Long id) {
    return repositorioDroga.findById(id).orElse(null);
  }

  @Override
  public void addDroga(Droga droga) {
    repositorioDroga.save(droga);
  }

  @Override
  public void updateDroga(Long id, Droga droga) {
    Droga drogaModificar = repositorioDroga.findById(id).orElse(null);
    drogaModificar.setNombre(droga.getNombre());
    drogaModificar.setPrecioCompra(droga.getPrecioCompra());
    drogaModificar.setPrecioVenta(droga.getPrecioVenta());
    drogaModificar.setUnidadDisponible(droga.getUnidadDisponible());
    drogaModificar.setUnidadVendida(droga.getUnidadVendida());
    repositorioDroga.save(drogaModificar);
  }

  @Override
  public void updateDroga(Droga droga) {
    repositorioDroga.save(droga);
  }

  @Override
  public void deleteById(Long id) {
    repositorioDroga.deleteById(id);
  }

  @Override
  public Long numDrogas() {
    return repositorioDroga.count();
  }

  @Override
  public Double totalVentas() {
    return repositorioDroga.totalVentas();
  }

  @Override
  public Double totalGanancias() {
    return repositorioDroga.totalGanancias();
  }
}
