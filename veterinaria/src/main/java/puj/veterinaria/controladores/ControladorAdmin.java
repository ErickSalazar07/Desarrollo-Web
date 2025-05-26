package puj.veterinaria.controladores;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import puj.veterinaria.entidades.Administrador;
import puj.veterinaria.entidades.UserEntity;
import puj.veterinaria.repositorios.RepositorioUserEntity;
import puj.veterinaria.seguridad.CustomUserDetailService;
import puj.veterinaria.seguridad.JWTGenerator;
import puj.veterinaria.servicios.administrador.AdminServicio;
import puj.veterinaria.servicios.excel.ExcelServicio;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAdmin {

  @Autowired
  AdminServicio administradorServicio;

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  RepositorioUserEntity userRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JWTGenerator jwtGenerator;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private ExcelServicio excelServicio;

  @Autowired
  private JavaMailSender mailSender;

  // POST
  // URL: http://localhost:8090/admin/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Administrador pasado por el body.")
  public ResponseEntity<Administrador> agregarAdministrador(@RequestBody Administrador administrador) {

    // Revisamos que nombre de usuario no exista
    if (userRepository.existsByUsername(administrador.getUsername())) {
      return new ResponseEntity<Administrador>(administrador, HttpStatus.BAD_REQUEST);
    }

    UserEntity userEntity = customUserDetailService.AdminToUser(administrador);
    administrador.setUser(userEntity);
    administrador.setId(null);
    return new ResponseEntity<>(administradorServicio.addAdministrador(administrador), HttpStatus.CREATED);
  }

  // URL: http://localhost:8090/admin/login
  @PostMapping("/login")
  @Operation(summary = "Permite loguear un Admin, pasado por el body.")
  public ResponseEntity<String> login(@RequestBody UserEntity admin) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    return new ResponseEntity<String>(token, HttpStatus.OK);
  }

  // GET
  // URL: http://localhost:8090/admin/admins
  @GetMapping("/admins")
  @Operation(summary = "Retorna todos los Administradores de la base de datos.")
  public List<Administrador> obtenerAdministradores() {
    return administradorServicio.findAll();
  }

  // URL: http://localhost:8090/admin/get-admin/1
  @GetMapping("/get-admin/{id}")
  @Operation(summary = "Retorna un Administrador por su ID.")
  public Administrador obtenerAdministradorPorId(@PathVariable("id") Long id) {
    return administradorServicio.findById(id);
  }

  // URL: http://localhost:8090/admin/get-admin-correo/admin@mail.com
  @GetMapping("/get-admin-correo/{correo}")
  @Operation(summary = "Retorna un Administrador por su correo.")
  public Administrador obtenerAdministradorPorCorreo(@PathVariable("correo") String correo) {
    return administradorServicio.findByCorreo(correo);
  }

  // URL: http://localhost:8090/admin/get-admin-username/admin123
  @GetMapping("/get-admin-username/{username}")
  @Operation(summary = "Retorna un Administrador por su username.")
  public Administrador obtenerAdministradorPorUsername(@PathVariable("username") String username) {
    return administradorServicio.findByUsername(username);
  }

  @GetMapping("/details")
  public ResponseEntity<Administrador> buscarAdmin() {
    Administrador admin = administradorServicio.findByUsername(
        SecurityContextHolder.getContext().getAuthentication().getName());
    if (admin == null) {
      return new ResponseEntity<Administrador>(admin, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Administrador>(admin, HttpStatus.OK);
  }

  @GetMapping("/get-excel-veterinarios")
  @Operation(summary = "Llama a realizar la operacion de enviar un excel de la tabla drogas, al administrador activo/logeado.")
  public ResponseEntity<Map<String, String>> enviarExcelTablaVeterinariosACorreoAdmin() {
    Administrador admin = administradorServicio
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

    if (admin == null)
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);

    excelServicio.generarExcelDeTablaVeterinario();

    MimeMessage mensaje = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

      helper.setFrom("health.animals.eed@gmail.com");
      helper.setTo(admin.getCorreo());
      helper.setSubject("Health Animals - Excel Tabla Veterinarios");

      try(var inputStream = Objects.requireNonNull(getClass()
          .getResourceAsStream("/templates/html/envio-excel-veterinarios.html"))) {
        String htmlTemplate = new String(inputStream.readAllBytes(),StandardCharsets.UTF_8);
        String htmlEmail = htmlTemplate.replace("${nombreDestinatario}", admin.getNombre());
        helper.setText(htmlEmail,true);
      }
      
      helper.addAttachment("Veterinarios.xlsx", new File("Veterinarios.xlsx"));
      helper.addInline("logo.png", new ClassPathResource("static/images/logo.png"));
      mailSender.send(mensaje);

      excelServicio.eliminarExcelPorNombre("Veterinarios.xlsx");
      return new ResponseEntity<>(Map.of("status","ok"),HttpStatus.OK);
    } catch (MessagingException e) {
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/get-excel-drogas")
  @Operation(summary = "Llama a realizar la operacion de enviar un excel de la tabla drogas, al administrador activo/logeado.")
  public ResponseEntity<Map<String, String>> enviarExcelTablaDrogasACorreoAdmin() {
    Administrador admin = administradorServicio
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

    if (admin == null)
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);

    excelServicio.generarExcelDeTablaDroga();

    MimeMessage mensaje = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

      helper.setFrom("health.animals.eed@gmail.com");
      helper.setTo(admin.getCorreo());
      helper.setSubject("Health Animals - Excel Tabla Drogas");

      try(var inputStream = Objects.requireNonNull(getClass()
          .getResourceAsStream("/templates/html/envio-excel-drogas.html"))) {
        String htmlTemplate = new String(inputStream.readAllBytes(),StandardCharsets.UTF_8);
        String htmlEmail = htmlTemplate.replace("${nombreDestinatario}", admin.getNombre());
        helper.setText(htmlEmail,true);
      }
      
      helper.addAttachment("Drogas.xlsx", new File("Drogas.xlsx"));
      helper.addInline("logo.png", new ClassPathResource("static/images/logo.png"));
      mailSender.send(mensaje);

      excelServicio.eliminarExcelPorNombre("Drogas.xlsx");
      return new ResponseEntity<>(Map.of("status","ok"),HttpStatus.OK);
    } catch (MessagingException e) {
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(Map.of("status", "fail"), HttpStatus.BAD_REQUEST);
    }
  }

  // PUT
  // URL: http://localhost:8090/admin/update/1
  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Administrador existente en la base de datos.")
  public void actualizarAdministrador(@RequestBody Administrador admin) {
    Administrador adminActualizar = administradorServicio.findById(admin.getId());

    adminActualizar.setNombre(admin.getNombre());
    adminActualizar.setCorreo(admin.getCorreo());
    adminActualizar.setCelular(admin.getCelular());
    adminActualizar.setUsername(admin.getUsername());

    administradorServicio.updateAdministrador(adminActualizar);
  }

  // DELETE
  // URL: http://localhost:8090/admin/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Administrador según su ID.")
  public void eliminarAdministrador(@PathVariable("id") Long id) {
    administradorServicio.deleteById(id);
  }
}
