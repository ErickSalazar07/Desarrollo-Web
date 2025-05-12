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
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.DTO.TratamientoDTO;
import puj.veterinaria.servicios.IDrogaServicio;
import puj.veterinaria.servicios.IMascotaServicio;
import puj.veterinaria.servicios.ITratamientoServicio;
import puj.veterinaria.servicios.IVeterinarioServicio;

@RestController
@RequestMapping("/tratamiento")
@CrossOrigin("http://localhost:4200")
public class ControladorTratamiento {

  @Autowired
  ITratamientoServicio tratamientoServicio;

  @Autowired
  IDrogaServicio drogaServicio;
  @Autowired
  IMascotaServicio mascotaServicio;

  @Autowired
  IVeterinarioServicio veterinarioServicio;

// Metodos PostMapping
  @PostMapping("/add")
  @Operation(summary = "Agrega un Tratamiento al db.")
  public ResponseEntity<Map<String,String>> agregarTratamiento(@RequestBody TratamientoDTO tratamientoDTO) {

    Tratamiento tratamiento = new Tratamiento(tratamientoDTO);
    Mascota mascota  = mascotaServicio.findById(tratamientoDTO.getMascotaID());
    Droga droga = drogaServicio.findById(tratamientoDTO.getDrogaAsignadaID());

    droga.setUnidadDisponible(droga.getUnidadDisponible()-1);
    droga.setUnidadVendida(droga.getUnidadVendida()+1);
    drogaServicio.updateDroga(droga);

    mascota.setEstadoActivo(true);;
    mascotaServicio.updateMascota(mascota);

    tratamiento.setDrogaAsignada(drogaServicio.findById(tratamientoDTO.getDrogaAsignadaID()));
    tratamiento.setMascota(mascotaServicio.findById(tratamientoDTO.getMascotaID()));
    tratamiento.setVeterinarioEncargado(veterinarioServicio.findByCedula(tratamientoDTO.getVeterinaroCedula()));
    tratamientoServicio.addTratamiento(tratamiento);

    Map<String,String> response = Map.of("mensaje","Tratamiento agregado");
    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }

// Metodos GetMapping

  @GetMapping("/tratamientos")
  @Operation(summary = "Retorna los Tratamientos de la db.")
  public ResponseEntity<List<Tratamiento>> obtenerTratamientos() {
      List<Tratamiento> lista = tratamientoServicio.findAll();
      ResponseEntity<List<Tratamiento>> response = new ResponseEntity<>(lista, HttpStatus.OK);
      return response;
  }

  

  @GetMapping("/get-tratamiento/{id}")
  @Operation(summary = "Retorna un Tratamiento, correspondiente al id que se provee.")
  public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable(value = "id") Long id) {
    ResponseEntity<Tratamiento> response = new ResponseEntity<>(tratamientoServicio.findById(id), HttpStatus.OK);
    return response;
  }

  @GetMapping("/tratamientos-mascota/{id}")
  @Operation(summary = "Retorna los Tratamientos de una Mascota, la cual corresponde al id.")
  public ResponseEntity<List<Tratamiento>> obtenerTratamientosMascota(@PathVariable(value = "id") Long idMascota) {
    ResponseEntity<List<Tratamiento>> response = new ResponseEntity<>(tratamientoServicio.findByMascotaId(idMascota), HttpStatus.OK);
    return response;
  }
  @GetMapping("/tratamientos-veterinario/{cedula}")
  @Operation(summary = "Retorna todos los tratamientos de un Veterinario dada la cédula.")
  public ResponseEntity<List<Tratamiento>> obtenerTratamientosVeterinario(@PathVariable(value = "cedula") String cedula) {
    ResponseEntity<List<Tratamiento>> response = new ResponseEntity<>(tratamientoServicio.tratamientosVeterinario(cedula), HttpStatus.OK);
    return response;
  }
  @GetMapping("/get-num-tratamientos-ultimo-mes")
  @Operation(summary = "Retorna el numero de tratamientos del ultimo mes.")
  public ResponseEntity<Long> obtenerNumTratamientosUltimoMes() {
    ResponseEntity<Long> response = new ResponseEntity<>(tratamientoServicio.cantidadTratamientosUltimoMes(), HttpStatus.OK);
    return response;
  }

  @GetMapping("get-num-tratamientos-tipo-droga/{droga}")
  @Operation(summary = "Retorna el numero de tratamientos que tengan asignado el tipo de droga que se pasa.")
  public ResponseEntity<Long> obtenerNumTratamientosPorTipoDroga(@PathVariable(value = "droga") String droga) {
    ResponseEntity<Long> response = new ResponseEntity<>(tratamientoServicio.cantidadTratamientosTipoMedicamento(droga), HttpStatus.OK);
    return response;
  }

  @GetMapping("get-top3-tratamientos-mas-unidad-vendida")
  @Operation(summary = "Retorna los 3 tratamientos con mas unidades vendidas.")
  public ResponseEntity<List<Tratamiento>> obtenerTop3TratamientosMasUnidadesVendidas() {
    ResponseEntity<List<Tratamiento>> response = new ResponseEntity<>(tratamientoServicio.top3TratamientosMasUnidadesVendidas(), HttpStatus.OK);
    return response;}



// Metodos PutMapping

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Tratamiento, correspondiente al id que se provee.")
  public ResponseEntity<String> actualizarTratamiento(@RequestBody Tratamiento tratamiento) {
    ResponseEntity<String> response = new ResponseEntity<>("Tratamiento Actualizado", HttpStatus.OK);
    return response;}

// Metodos DeleteMapping

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Tratamiento, de la db, correspondiente al id.")
  public ResponseEntity<String> eliminarTratamiento(@PathVariable(value = "id") Long id) {
    tratamientoServicio.deleteById(id);
    ResponseEntity<String> response = new ResponseEntity<>("Tratamiento Eliminado", HttpStatus.OK);
    return response;
  }
}
