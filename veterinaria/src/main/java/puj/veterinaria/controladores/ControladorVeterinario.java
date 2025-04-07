package puj.veterinaria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.servicios.ITratamientoServicio;
import puj.veterinaria.servicios.IVeterinarioServicio;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin("http://localhost:4200")
public class ControladorVeterinario {

  @Autowired
  IVeterinarioServicio veterinarioServicio;

  @Autowired
  ITratamientoServicio tratamientoServicio;

// Metodos PostMapping


  // URL: http://localhost:8090/veterinario/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Veterinario a la db, el cual es pasado por el cuerpo de la peticion.")
  public void agregarVeterinario(@RequestBody Veterinario veterinario) {
    veterinarioServicio.addVeterinario(veterinario);
  }

// Metodos GetMapping

  // URL: http://localhost:8090/veterinario/veterinarios
  @GetMapping("/veterinarios")
  @Operation(summary = "Retorna todos los Veterinarios de la db.")
  public List<Veterinario> obtenerVeterinarios() {
    return veterinarioServicio.findAll();
  }

  // URL: http://localhost:8090/veterinario/get-veterinario/1
  @GetMapping("/get-veterinario/{id}")
  @Operation(summary = "Retorna un Veterinario, el cual corresponde al id que se para por la URL.")
  public Veterinario obtenerVeterinario(@PathVariable(value = "id") Long id) {
    return veterinarioServicio.findById(id);
  }

// Metodos PutMapping

  // URL: http://localhost:8090/veterinario/update/1
  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Veterinario, el cual es pasado por el cuerpo de la peticion.")
  public void actualizarVeterinario(@RequestBody Veterinario veterinario) {
    veterinarioServicio.updateVeterinario(veterinario);
  }

// Metodos DeleteMapping

  // URL: http://localhost:8090/veterinario/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Cliente de la db, el cual corresponde al id pasado por la URL.")
  public void eliminarVeterinario(@PathVariable(value = "id") Long id) {
    veterinarioServicio.deleteById(id);
  }
}
