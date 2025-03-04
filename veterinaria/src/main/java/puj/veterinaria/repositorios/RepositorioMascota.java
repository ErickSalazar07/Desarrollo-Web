package puj.veterinaria.repositorios;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;

@Repository
public class RepositorioMascota {
  Map<Integer,Mascota> baseDatosMascotas;
  public RepositorioMascota() {
    // lista de tratamientienso 

    baseDatosMascotas = new HashMap<>();
    baseDatosMascotas.put(1, new Mascota(1, "Pachini","Gato",5,4.5,"VIF",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-pachini.jpg?raw=true"
    , Arrays.asList(new Tratamiento("Antibioticos"),
    new Tratamiento("Antiretrovirales"))));
    baseDatosMascotas.put(2, new Mascota(2, "Zeus","Perro",9,11.7,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-zeus.jpg?raw=true", null));
    baseDatosMascotas.put(3, new Mascota(3, "Figaro","Perro",9,19.7,"Cataratas",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-figaro.jpg?raw=true",
    Arrays.asList(new Tratamiento("Retrovirales"), new Tratamiento("Vacuna Rabia"))));
    baseDatosMascotas.put(4, new Mascota(4, "Lola","Gato",3,6.0,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-lola.jpg?raw=true",null));
  }

  public Mascota findById(Integer id) {
    return baseDatosMascotas.get(id);
  }

  public Collection<Mascota> findAll() {
    return baseDatosMascotas.values();
  }

  public void deleteById(Integer id) {
    baseDatosMascotas.remove(id);
  }

  public void updateMascota(Mascota mascota) {
    baseDatosMascotas.put(mascota.getId(), mascota);
  }

  public void addMascota(Mascota mascota) {
    // el tamaño nos dice el id actual, se busca el siguiente a ese
    Integer idLibre = baseDatosMascotas.keySet().stream().max(Integer::compareTo).orElse(0)+1; 
    mascota.setId(idLibre);
    baseDatosMascotas.put(idLibre,mascota);
  }
}
