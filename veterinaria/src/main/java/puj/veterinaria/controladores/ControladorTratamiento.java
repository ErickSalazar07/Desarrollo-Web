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
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.servicios.ITratamientoServicio;

@RestController
@RequestMapping("/tratamiento")
@CrossOrigin("http://localhost:4200")
public class ControladorTratamiento {

  @Autowired
  ITratamientoServicio tratamientoServicio;

// Metodos PostMapping
  @PostMapping("/add")
  @Operation(summary = "Agrega un Tratamiento al db.")
  public void agregarTratamiento(@RequestBody Tratamiento tratamiento) {
    tratamientoServicio.addTratamiento(tratamiento);
  }

// Metodos GetMapping

  @GetMapping("/tratamientos")
  @Operation(summary = "Retorna los Tratamientos de la db.")
  public List<Tratamiento> obtenerTratamientos() {
    return tratamientoServicio.findAll();
  }

  @GetMapping("/get-tratamiento/{id}")
  @Operation(summary = "Retorna un Tratamiento, correspondiente al id que se provee.")
  public Tratamiento obtenerTratamiento(@PathVariable(value = "id") Long id) {
    return tratamientoServicio.findById(id);
  }

  @GetMapping("/tratamientos-mascota/{id}")
  @Operation(summary = "Retorna los Tratamientos de una Mascota, la cual corresponde al id.")
  public List<Tratamiento> obtenerTratamientosMascota(@PathVariable(value = "id") Long idMascota) {
    return tratamientoServicio.findByMascotaId(idMascota);
  }

  @GetMapping("/get-num-tratamientos-ultimo-mes")
  @Operation(summary = "Retorna el numero de tratamientos del ultimo mes.")
  public Long obtenerNumTratamientosUltimoMes() {
    return tratamientoServicio.cantidadTratamientosUltimoMes();
  }

  @GetMapping("get-num-tratamientos-tipo-droga/{droga}")
  @Operation(summary = "Retorna el numero de tratamientos que tengan asignado el tipo de droga que se pasa.")
  public Long obtenerNumTratamientosPorTipoDroga(@PathVariable(value = "droga") String droga) {
    return tratamientoServicio.cantidadTratamientosTipoMedicamento(droga);
  }

  @GetMapping("get-top3-tratamientos-mas-unidad-vendida")
  @Operation(summary = "Retorna los 3 tratamientos con mas unidades vendidas.")
  public List<Tratamiento> obtenerTop3TratamientosMasUnidadesVendidas() {
    return tratamientoServicio.top3TratamientosMasUnidadesVendidas();
  }

// Metodos PutMapping

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Tratamiento, correspondiente al id que se provee.")
  public void actualizarTratamiento(@RequestBody Tratamiento tratamiento) {
    tratamientoServicio.updateTratamiento(tratamiento);
  }

// Metodos DeleteMapping

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Tratamiento, de la db, correspondiente al id.")
  public void eliminarTratamiento(@PathVariable(value = "id") Long id) {
    tratamientoServicio.deleteById(id);
  }
}
