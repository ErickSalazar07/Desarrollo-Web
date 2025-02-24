package puj.veterinaria.repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Mascota;

@Repository
public class RepositorioMascota {
  Map<Integer,Mascota> baseDatosMascotas;
  public RepositorioMascota() {
    baseDatosMascotas = new HashMap<>();
    baseDatosMascotas.put(1, new Mascota("Pachini","Gato",5,4.5,null,
    "https://imagen-pachini.png",null));
    baseDatosMascotas.put(2, new Mascota("Zeus","Perro",9,11.7,null,
    "https://imagen-zeus.png",null));
    baseDatosMascotas.put(3, new Mascota("Ares","Perro",9,19.7,null,
    "https://imagen-ares.png",null));
  }

  public Mascota findById(Integer id) {
    return baseDatosMascotas.get(id);
  }

  public Collection<Mascota> findAll() {
    return baseDatosMascotas.values();
  }
}
