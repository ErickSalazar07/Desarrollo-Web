package puj.veterinaria.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.DTO.MascotaDTO;
import puj.veterinaria.servicios.cliente.IClienteServicio;
import puj.veterinaria.servicios.mascota.IMascotaServicio;

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
  public ResponseEntity<Mascota> agregarMascota(@RequestBody MascotaDTO mascotaPeticion) {
    Mascota mascotaAgregar = new Mascota(mascotaPeticion);
    mascotaAgregar.setCliente(clienteServicio.findByCedula(mascotaPeticion.getCedulaCliente()));
    return new ResponseEntity<>(mascotaServicio.addMascota(mascotaAgregar),HttpStatus.CREATED);
  }

// Metodos @GetMapping

  // URL: http://localhost:8090/mascota/mascotas
  @GetMapping("/mascotas")
  @Operation(summary = "Retornar todas las Mascotas en la db.")
  public ResponseEntity<List<Mascota>> obtenerMascotas() {
    return new ResponseEntity<>(mascotaServicio.findAll(), HttpStatus.OK);  
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/get-mascota/1 
  @GetMapping("/get-mascota/{id}")
  @Operation(summary = "Retornar una Mascota la cual corresponda con el id, que se pasa por la URL.")
  public ResponseEntity<MascotaDTO> obtenerMascota(@PathVariable(value = "id") Long id) {
    return new ResponseEntity<>(new MascotaDTO(mascotaServicio.findById(id)),HttpStatus.OK);
  }

  // URL: http://localhost:8090/mascota/mascotas-cliente/1 
  @GetMapping("/mascotas-clienteNoAut/{cedula}")
  @Operation(summary = "Retorna todas las Mascotas de un Cliente, por la cedula del Cliente.")
  public ResponseEntity<List<Mascota>> obtenerMascotasCliente(@PathVariable(value = "cedula") String cedula) {
    return new ResponseEntity<>(mascotaServicio.findByClienteCedula(cedula),HttpStatus.OK);
  }
//-------------------TOKEN--------------------------------
@GetMapping("/mascotas-cliente")
@Operation(summary = "Retorna todas las Mascotas del Cliente autenticado.")
public ResponseEntity<List<Mascota>> obtenerMascotasClienteAutenticado() {
  // Obtener el correo desde el JWT
  String correo = SecurityContextHolder.getContext().getAuthentication().getName();
  Cliente cliente = clienteServicio.findByCorreo(correo);

  if (cliente == null) {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  List<Mascota> mascotas = mascotaServicio.findByClienteCedula(cliente.getCedula());
  return new ResponseEntity<>(mascotas, HttpStatus.OK);
}


  @GetMapping("/get-num-mascotas-activas")
  @Operation(summary = "Retorna el numero de mascotas que estan activas.")
  public ResponseEntity<Long> obtenerNumMascotasActivas() {
    return new ResponseEntity<>(mascotaServicio.cantidadMascotasActivas(),HttpStatus.OK);
  }

  @GetMapping("/get-num-mascotas")
  @Operation(summary = "Retorna el numero de mascotas en la veterinaria.")
  public ResponseEntity<Long> obtenerNumMascotas() {
    return new ResponseEntity<>(mascotaServicio.numeroMascotas(),HttpStatus.OK);
  }

// Metodos PutMapping

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/cambiar-estado/1
  @PutMapping("/cambiar-estado/{id}")
  @Operation(summary = "Cambia el estado de una Mascota, la cual corresponda al id, que se pasa por la URL.")
  public ResponseEntity<Mascota> cambiarEstado(@PathVariable("id") Long id) {
    return new ResponseEntity<>(mascotaServicio.cambiarEstadoById(id),HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza una Mascota, la cual se pasa por el cuerpo de la peticion.")
  public ResponseEntity<Mascota> actualizarMascota(@RequestBody MascotaDTO mascotaDTO) {
    Mascota mascotaActualizar = mascotaServicio.findById(mascotaDTO.getId());

    mascotaActualizar.setNombre(mascotaDTO.getNombre());
    mascotaActualizar.setEdad(mascotaDTO.getEdad());
    mascotaActualizar.setRaza(mascotaDTO.getRaza());
    mascotaActualizar.setPeso(mascotaDTO.getPeso());
    mascotaActualizar.setFoto(mascotaDTO.getFoto());
    mascotaActualizar.setEnfermedad(mascotaDTO.getEnfermedad());
    mascotaActualizar.setEstadoActivo(mascotaDTO.getEstadoActivo());
    mascotaActualizar.setCliente(clienteServicio.findByCedula(mascotaDTO.getCedulaCliente()));

    return new ResponseEntity<>(mascotaServicio.updateMascota(mascotaActualizar),HttpStatus.OK);
  }

// Metodos DeleteMapping

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina una Mascota en la db, la cual corresponde al id que se pasa por la URL.")
  public ResponseEntity<Map<String,String>> eliminarMascota(@PathVariable("id") Long id) {
    mascotaServicio.deleteById(id);
    return new ResponseEntity<>(Map.of("mensaje","eliminado"),HttpStatus.OK);
  }
}
