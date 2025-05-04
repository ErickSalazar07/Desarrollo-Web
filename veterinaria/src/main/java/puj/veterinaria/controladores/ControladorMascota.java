package puj.veterinaria.controladores;

import java.util.List;

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
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.DTO.MascotaDTO;
import puj.veterinaria.servicios.IClienteServicio;
import puj.veterinaria.servicios.IMascotaServicio;

@RestController
@RequestMapping("/mascota")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorMascota {
  
  @Autowired
  IMascotaServicio mascotaServicio; 

  @Autowired
  IClienteServicio clienteServicio;
// Metodos PostMapping

  @PostMapping("/add")
  @Operation(summary = "Agrega una Mascota a la db, la cual es pasado por el cuerpo de la peticion.")
  public void agregarMascota(@RequestBody MascotaDTO mascotaPeticion) {
    Mascota mascotaAgregar = new Mascota(mascotaPeticion);
    mascotaAgregar.setCliente(clienteServicio.findByCedula(mascotaPeticion.getCedulaCliente()));
    mascotaServicio.addMascota(mascotaAgregar);
  }

// Metodos @GetMapping

  // URL: http://localhost:8090/mascota/mascotas
  @GetMapping("/mascotas")
  @Operation(summary = "Retornar todas las Mascotas en la db.")
  public ResponseEntity<List<Mascota>> obtenerMascotas() {
    ResponseEntity<List<Mascota>> response = new ResponseEntity<>(mascotaServicio.findAll(), HttpStatus.OK);  
    return response;
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/get-mascota/1 
  @GetMapping("/get-mascota/{id}")
  @Operation(summary = "Retornar una Mascota la cual corresponda con el id, que se pasa por la URL.")
  public MascotaDTO obtenerMascota(@PathVariable(value = "id") Long id) {
    return new MascotaDTO(mascotaServicio.findById(id));
  }

  // URL: http://localhost:8090/mascota/mascotas-cliente/1 
  @GetMapping("/mascotas-cliente/{cedula}")
  @Operation(summary = "Retorna todas las Mascotas de un Cliente, por la cedula del Cliente.")
  public List<Mascota> obtenerMascotasCliente(@PathVariable(value = "cedula") String cedula) {
    return mascotaServicio.findByClienteCedula(cedula);
  }

  @GetMapping("/get-num-mascotas-activas")
  @Operation(summary = "Retorna el numero de mascotas que estan activas.")
  public Long obtenerNumMascotasActivas() {
    return mascotaServicio.cantidadMascotasActivas();
  }

  @GetMapping("/get-num-mascotas")
  @Operation(summary = "Retorna el numero de mascotas en la veterinaria.")
  public Long obtenerNumMascotas() {
    return mascotaServicio.numeroMascotas();
  }

// Metodos PutMapping

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/cambiar-estado/1
  @PutMapping("/cambiar-estado/{id}")
  @Operation(summary = "Cambia el estado de una Mascota, la cual corresponda al id, que se pasa por la URL.")
  public void cambiarEstado(@PathVariable("id") Long id) {
    mascotaServicio.cambiarEstadoById(id);
  }

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza una Mascota, la cual se pasa por el cuerpo de la peticion.")
  public void actualizarMascota(@RequestBody MascotaDTO mascotaDTO) {
    Mascota mascotaActualizar = mascotaServicio.findById(mascotaDTO.getId());

    mascotaActualizar.setNombre(mascotaDTO.getNombre());
    mascotaActualizar.setEdad(mascotaDTO.getEdad());
    mascotaActualizar.setRaza(mascotaDTO.getRaza());
    mascotaActualizar.setPeso(mascotaDTO.getPeso());
    mascotaActualizar.setFoto(mascotaDTO.getFoto());
    mascotaActualizar.setEnfermedad(mascotaDTO.getEnfermedad());
    mascotaActualizar.setEstadoActivo(mascotaDTO.getEstadoActivo());
    mascotaActualizar.setCliente(clienteServicio.findByCedula(mascotaDTO.getCedulaCliente()));

    mascotaServicio.updateMascota(mascotaActualizar);
  }

// Metodos DeleteMapping

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina una Mascota en la db, la cual corresponde al id que se pasa por la URL.")
  public void eliminarMascota(@PathVariable("id") Long id) {
    mascotaServicio.deleteById(id);
  }
}
