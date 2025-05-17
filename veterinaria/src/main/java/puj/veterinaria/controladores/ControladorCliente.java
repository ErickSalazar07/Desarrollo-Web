package puj.veterinaria.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import puj.veterinaria.entidades.UserEntity;
import puj.veterinaria.repositorios.RepositorioUserEntity;
import puj.veterinaria.seguridad.CustomUserDetailService;
import puj.veterinaria.servicios.cliente.ClienteServicio;


@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {

  @Autowired
  ClienteServicio clienteServicio;

  @Autowired
  RepositorioUserEntity userRepository;

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  AuthenticationManager authenticationManager;

// Metodos PostMapping

  // URL: http://localhost:8090/cliente/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Cliente, pasado por el body.")
  public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
    //

    //Revisamos que nombre de usuario no exista
    if(userRepository.existsByUsername(cliente.getCorreo())) {
      return new ResponseEntity<Cliente>(cliente, HttpStatus.BAD_REQUEST);
    }

    UserEntity userEntity = customUserDetailService.clienteToUser(cliente);
    cliente.setUser(userEntity);
    cliente.setId(null);
    return new ResponseEntity<>(clienteServicio.addCliente(cliente),HttpStatus.CREATED); 
   }
  // URL: http://localhost:8090/cliente/login
   @PostMapping("/login")
  @Operation(summary = "Permite loguear un Cliente, pasado por el body.")
   public ResponseEntity login(@RequestBody Cliente cliente) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(cliente.getCorreo(), cliente.getCedula())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return new ResponseEntity<String>("Usuario ingresa con éxito", HttpStatus.OK);
   }

// Metodos GetMapping

  // URL: http://localhost:8090/cliente/clientes
  @GetMapping("/clientes")
  @Operation(summary = "Retorna todos los Clientes de la bd.")
  public ResponseEntity<List<Cliente>> obtenerClientes() {
    return new ResponseEntity<>(clienteServicio.findAll(),HttpStatus.OK);
  }

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/get-cliente/1
  @GetMapping("/get-cliente/{id}")
  @Operation(summary = "Retorna 1 Cliente, el cual tiene el id, que se especifica.")
  public ResponseEntity<Cliente> obtenerCliente(@PathVariable(value = "id") Long id) {
    return new ResponseEntity<>(clienteServicio.findById(id),HttpStatus.OK);
  }

  @GetMapping("/get-cliente-cedula/{cedula}")
  @Operation(summary = "Retorna 1 Cliente, correspondiente a la cedula.")
  public ResponseEntity<Cliente> obtenerClientePorCedula(@PathVariable(value = "cedula") String cedula) {
    return new ResponseEntity<>(clienteServicio.findByCedula(cedula),HttpStatus.OK);
  }


// Metodos PutMapping

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/update/1
  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Cliente existente en la base de datos.")
  public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente) {
    Cliente clienteActualizar = clienteServicio.findById(cliente.getId());

    clienteActualizar.setNombre(cliente.getNombre());
    clienteActualizar.setCorreo(cliente.getCorreo());
    clienteActualizar.setCelular(cliente.getCelular());

    return new ResponseEntity<>(clienteServicio.updateCliente(clienteActualizar),HttpStatus.OK);
  }

// Metodos DeleteMapping

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Cliente, el cual corresponde al id que se especifica.")
  public ResponseEntity<Map<String,String>> eliminarCliente(@PathVariable("id") Long id) {
    clienteServicio.deleteById(id);
    return new ResponseEntity<>(Map.of("mensaje","eliminado"),HttpStatus.OK);
  }
} 
