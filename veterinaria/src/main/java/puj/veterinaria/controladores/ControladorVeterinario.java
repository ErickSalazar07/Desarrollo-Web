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
import puj.veterinaria.entidades.Administrador;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.UserEntity;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioUserEntity;
import puj.veterinaria.seguridad.CustomUserDetailService;
import puj.veterinaria.seguridad.JWTGenerator;
import puj.veterinaria.servicios.veterinario.IVeterinarioServicio;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")

public class ControladorVeterinario {

  @Autowired
  IVeterinarioServicio veterinarioServicio;

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private RepositorioUserEntity userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
   JWTGenerator jwtGenerator;


// Metodos PostMapping


  // URL: http://localhost:8090/veterinario/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Veterinario a la db, el cual es pasado por el cuerpo de la peticion.")
  public ResponseEntity<Veterinario> agregarVeterinario(@RequestBody Veterinario veterinario) {
    if(userRepository.existsByUsername(veterinario.getCedula())){
      return new ResponseEntity<Veterinario>(veterinario, HttpStatus.BAD_REQUEST);
    }

    UserEntity user = customUserDetailService.veterinarioToUser(veterinario);
    veterinario.setUser(user);
    veterinario.setId(null);
    return new ResponseEntity<>(veterinarioServicio.addVeterinario(veterinario),HttpStatus.CREATED);

  }
  // URL: http://localhost:8090/veterinario/login
   @PostMapping("/login")
  @Operation(summary = "Permite loguear un Veterinario, pasado por el body.")
   public ResponseEntity login(@RequestBody Veterinario vet) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(vet.getCedula(), vet.getContrasena())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    return new ResponseEntity<String>(token, HttpStatus.OK);
   }

// Metodos GetMapping

  // URL: http://localhost:8090/veterinario/veterinarios
  @GetMapping("/veterinarios")
  @Operation(summary = "Retorna todos los Veterinarios de la db.")
  public ResponseEntity<List<Veterinario>> obtenerVeterinarios() {
    return new ResponseEntity<>(veterinarioServicio.findAll(),HttpStatus.OK);
  }


  // URL: http://localhost:8090/veterinario/get-veterinario/1
  @GetMapping("/get-veterinario/{id}")
  @Operation(summary = "Retorna un Veterinario, el cual corresponde al id que se para por la URL.")
  public ResponseEntity<Veterinario> obtenerVeterinario(@PathVariable(value = "id") Long id) {
    return new ResponseEntity<>(veterinarioServicio.findById(id),HttpStatus.OK);
  }

  @GetMapping("/get-veterinario-cedula/{cedula}")
  @Operation(summary = "Retorna un Veterinario, el cual corresponde a la cedula que se pasa por la URL.")
  public ResponseEntity<Veterinario> obtenerVeterinarioCedula(@PathVariable(value = "cedula") String cedula) {
    return new ResponseEntity<>(veterinarioServicio.findByCedula(cedula),HttpStatus.OK);
  }

  @GetMapping("/get-num-veterinarios-activos")
  @Operation(summary = "Retorna el numero de veterinarios activos.")
  public ResponseEntity<Long> obtenerNumVeterinariosActivos() {
    return new ResponseEntity<>(veterinarioServicio.cantidadVeterinariosActivos(),HttpStatus.OK);
  }

  @GetMapping("/get-num-veterinarios-inactivos")
  @Operation(summary = "Retorna el numero de veterinarios inactivos.")
  public ResponseEntity<Long> obtenerNumVeterinariosInactivos() {
    return new ResponseEntity<>(veterinarioServicio.cantidadVeterinariosInactivos(),HttpStatus.OK);
  }
  @GetMapping("/details")
  public ResponseEntity<Veterinario> buscarVeterinario() {
   Veterinario vet = veterinarioServicio.findByCedula(
    SecurityContextHolder.getContext().getAuthentication().getName()
   );
   if(vet == null){
    return new ResponseEntity<Veterinario>(vet, HttpStatus.NOT_FOUND);
   }
   return new ResponseEntity<Veterinario>(vet, HttpStatus.OK);
  }
// Metodos PutMapping

  // URL: http://localhost:8090/veterinario/update/1
  @PutMapping("/update-id/{id}")
  @Operation(summary = "Actualiza un Veterinario, el cual es pasado por el cuerpo de la peticion.")
  public ResponseEntity<Veterinario> actualizarVeterinario(@RequestBody Veterinario veterinario) {
    return new ResponseEntity<>(veterinarioServicio.updateVeterinario(veterinario),HttpStatus.OK);
  }

  // URL: https://localhost:8090/veterinario/update/104828483
  @PutMapping("/update-cc/{cedula}")
  @Operation(summary = "Actualiza un Veterinario, correspondiente a la cedula que se pasa.")
  public ResponseEntity<Veterinario> actualizarVeterinarioPorCedula(@RequestBody Veterinario veterinario) {
    Veterinario veterinarioActualizar = veterinarioServicio.findByCedula(veterinario.getCedula());
    veterinarioActualizar.setNombre(veterinario.getNombre());
    veterinarioActualizar.setActivo(veterinario.getActivo());
    veterinarioActualizar.setContrasena(veterinario.getContrasena());
    veterinarioActualizar.setEspecialidad(veterinario.getEspecialidad());
    veterinarioActualizar.setFoto(veterinario.getFoto());
    return new ResponseEntity<>(veterinarioServicio.updateVeterinario(veterinarioActualizar),HttpStatus.OK);
  }
  
  @PutMapping("/update-estado/{cedula}")
  public ResponseEntity<Veterinario> cambiarEstadoVeterinarioPorCedula(@PathVariable(value = "cedula") String cedula) {
    Veterinario veterinario = veterinarioServicio.findByCedula(cedula);
    veterinario.setActivo(!veterinario.getActivo());
    return new ResponseEntity<>(veterinarioServicio.updateVeterinario(veterinario),HttpStatus.OK);
  }

// Metodos DeleteMapping

  // URL: http://localhost:8090/veterinario/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Cliente de la db, el cual corresponde al id pasado por la URL.")
  public ResponseEntity<Map<String,String>> eliminarVeterinario(@PathVariable(value = "id") Long id) {
    veterinarioServicio.deleteById(id);
    return new ResponseEntity<>(Map.of("mensaje","eliminado"),HttpStatus.OK);
  }
}
