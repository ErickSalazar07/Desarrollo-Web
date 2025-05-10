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
import puj.veterinaria.entidades.Droga;
import puj.veterinaria.servicios.IDrogaServicio;

@RestController
@RequestMapping("/droga")
@CrossOrigin("http://localhost:4200")
public class ControladorDroga {
  @Autowired
  IDrogaServicio drogaServicio;

// Metodos PostMapping

  @PostMapping("/add")
  @Operation(summary = "Agrega una nueva Droga la cual es pasada por el cuerpo de la peticion, a la db.")
  public void agregarDroga(@RequestBody Droga droga) {
    drogaServicio.addDroga(droga);
  }

// Metodos GetMapping

  // URL: http://localhost:8090/droga/drogas
  @GetMapping("/drogas")
  @Operation(summary = "Retorna todas las Drogas de la db.")
  public List<Droga> obtenerDrogas() {
    return drogaServicio.findAll();
  }

  // URL: http://localhost:8090/droga/get-droga/1
  @GetMapping("/get-droga/{id}")
  @Operation(summary = "Retorna una Droga, correspondiente al id que se provee.")
  public Droga obtenerDroga(@PathVariable(value = "id") Long id) {
    return drogaServicio.findById(id);
  }

  @GetMapping("/get-total-ventas")
  @Operation(summary = "Retorna el total de ventas de drogas.")
  public Double obtenerTotalVentas() {
    return drogaServicio.totalVentas();
  }

  @GetMapping("/get-total-ganancias")
  @Operation(summary = "Retorna el total de ganancias de drogas.")
  public Double obtenerTotalGanancias() {
    return drogaServicio.totalGanancias();
  }

// Metodos PutMapping

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza una Droga, correspondiente al id que se provee.")
  public void actualizarDroga(@RequestBody Droga droga) {
    drogaServicio.updateDroga(droga);
  }

// Metodos DeleteMapping

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina una Droga, correspondiente al id que se provee.")
  public void eliminarDroga(@PathVariable(value = "id") Long id) {
    drogaServicio.deleteById(id);
  }
}
