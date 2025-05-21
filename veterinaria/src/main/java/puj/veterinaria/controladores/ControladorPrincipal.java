package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import puj.veterinaria.entidades.UserEntity;
import puj.veterinaria.seguridad.JWTGenerator;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

  @Autowired
  AuthenticationManager authenticationManager;

  
  @Autowired
   JWTGenerator jwtGenerator;

  // URL: http://localhost:8090/
  @GetMapping("")
  public String paginaPrincipal() {
    return "html/index";
  }

  // URL: http://localhost:8090/index.html
  @GetMapping("index.html")
  public String paginaIndex() {
    return "html/index";
  }

  @PostMapping("/login")
  @Operation(summary = "Permite loguear un usuario, pasado por el body.")
  //No recibí un tipo cliente
   public ResponseEntity login(@RequestBody UserEntity user) {
    Authentication authentication = authenticationManager.authenticate(
      //new UsernamePasswordAuthenticationToken(cliente.getCorreo(), cliente.getCedula())
      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    return new ResponseEntity<String>(token, HttpStatus.OK);
   }

}
