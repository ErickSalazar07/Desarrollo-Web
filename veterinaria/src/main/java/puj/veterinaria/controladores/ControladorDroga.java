package puj.veterinaria.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import puj.veterinaria.servicios.droga.IDrogaServicio;

@RestController
@RequestMapping("/droga")
@CrossOrigin("http://localhost:4200")
public class ControladorDroga {

  @Autowired
  IDrogaServicio drogaServicio;

// Metodos PostMapping

  @PostMapping("/add")
  @Operation(summary = "Agrega una nueva Droga la cual es pasada por el cuerpo de la peticion, a la db.")
  public ResponseEntity<Droga> agregarDroga(@RequestBody Droga droga) {
    return new ResponseEntity<>(drogaServicio.addDroga(droga),HttpStatus.CREATED);
  }

// Metodos GetMapping

  // URL: http://localhost:8090/droga/drogas
  @GetMapping("/drogas")
  @Operation(summary = "Retorna todas las Drogas de la db.")
  public ResponseEntity<List<Droga>> obtenerDrogas() {
    return new ResponseEntity<>(drogaServicio.findAll(),HttpStatus.OK);
  }

  // URL: http://localhost:8090/droga/get-droga/1
  @GetMapping("/get-droga/{id}")
  @Operation(summary = "Retorna una Droga, correspondiente al id que se provee.")
  public ResponseEntity<Droga> obtenerDroga(@PathVariable(value = "id") Long id) {
    return new ResponseEntity<>(drogaServicio.findById(id),HttpStatus.OK);
  }

  @GetMapping("/get-total-ventas")
  @Operation(summary = "Retorna el total de ventas de drogas.")
  public ResponseEntity<Double> obtenerTotalVentas() {
    return new ResponseEntity<>(drogaServicio.totalVentas(),HttpStatus.OK);
  }

  @GetMapping("/get-total-ganancias")
  @Operation(summary = "Retorna el total de ganancias de drogas.")
  public ResponseEntity<Double> obtenerTotalGanancias() {
    return new ResponseEntity<>(drogaServicio.totalGanancias(),HttpStatus.OK);
  }

// Metodos PutMapping

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza una Droga, correspondiente al id que se provee.")
  public ResponseEntity<Droga> actualizarDroga(@RequestBody Droga droga) {
    return new ResponseEntity<>(drogaServicio.updateDroga(droga),HttpStatus.OK);
  }

// Metodos DeleteMapping

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina una Droga, correspondiente al id que se provee.")
  public ResponseEntity<Map<String,String>> eliminarDroga(@PathVariable(value = "id") Long id) {
    drogaServicio.deleteById(id);
    return new ResponseEntity<>(Map.of("mensaje","eliminado"),HttpStatus.OK);
  }
}
