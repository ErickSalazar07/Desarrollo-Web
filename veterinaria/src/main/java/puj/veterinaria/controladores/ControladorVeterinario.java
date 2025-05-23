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
import puj.veterinaria.servicios.IVeterinarioServicio;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")

public class ControladorVeterinario {

  @Autowired
  IVeterinarioServicio veterinarioServicio;


// Metodos PostMapping


  // URL: http://localhost:8090/veterinario/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Veterinario a la db, el cual es pasado por el cuerpo de la peticion.")
  public void agregarVeterinario(@RequestBody Veterinario veterinario) {
    veterinario.setId(null);
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

  @GetMapping("/get-veterinario-cedula/{cedula}")
  @Operation(summary = "Retorna un Veterinario, el cual corresponde a la cedula que se pasa por la URL.")
  public Veterinario obtenerVeterinarioCedula(@PathVariable(value = "cedula") String cedula) {
    return veterinarioServicio.findByCedula(cedula);
  }

  @GetMapping("/get-num-veterinarios-activos")
  @Operation(summary = "Retorna el numero de veterinarios activos.")
  public Long obtenerNumVeterinariosActivos() {
    return veterinarioServicio.cantidadVeterinariosActivos();
  }

  @GetMapping("/get-num-veterinarios-inactivos")
  @Operation(summary = "Retorna el numero de veterinarios inactivos.")
  public Long obtenerNumVeterinariosInactivos() {
    return veterinarioServicio.cantidadVeterinariosInactivos();
  }

// Metodos PutMapping

  // URL: http://localhost:8090/veterinario/update/1
  @PutMapping("/update-id/{id}")
  @Operation(summary = "Actualiza un Veterinario, el cual es pasado por el cuerpo de la peticion.")
  public void actualizarVeterinario(@RequestBody Veterinario veterinario) {
    veterinarioServicio.updateVeterinario(veterinario);
  }

  // URL: https://localhost:8090/veterinario/update/104828483
  @PutMapping("/update-cc/{cedula}")
  @Operation(summary = "Actualiza un Veterinario, correspondiente a la cedula que se pasa.")
  public void actualizarVeterinarioPorCedula(@RequestBody Veterinario veterinario) {
    Veterinario veterinarioActualizar = veterinarioServicio.findByCedula(veterinario.getCedula());
    veterinarioActualizar.setNombre(veterinario.getNombre());
    veterinarioActualizar.setActivo(veterinario.getActivo());
    veterinarioActualizar.setContrasena(veterinario.getContrasena());
    veterinarioActualizar.setEspecialidad(veterinario.getEspecialidad());
    veterinarioActualizar.setFoto(veterinario.getFoto());
    veterinarioServicio.updateVeterinario(veterinarioActualizar);
  }
  
  @PutMapping("/update-estado/{cedula}")
  public void cambiarEstadoVeterinarioPorCedula(@PathVariable(value = "cedula") String cedula) {
    Veterinario veterinario = veterinarioServicio.findByCedula(cedula);
    veterinario.setActivo(!veterinario.getActivo());
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
