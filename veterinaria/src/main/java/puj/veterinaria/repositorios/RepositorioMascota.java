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
    baseDatosMascotas.put(1, new Mascota("Pachini","Gato",5,4.5,"VIF",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-pachini.jpg?raw=true",null));
    baseDatosMascotas.put(2, new Mascota("Zeus","Perro",9,11.7,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-zeus.jpg?raw=true",null));
    baseDatosMascotas.put(3, new Mascota("Figaro","Perro",9,19.7,"Cataratas",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-figaro.jpg?raw=true",null));
    baseDatosMascotas.put(4, new Mascota("Lola","Gato",3,6.0,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-lola.jpg?raw=true",null));
  }

  public Mascota findById(Integer id) {
    return baseDatosMascotas.get(id);
  }

  public Collection<Mascota> findAll() {
    return baseDatosMascotas.values();
  }
}
