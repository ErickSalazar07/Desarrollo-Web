package puj.veterinaria.controladores;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import puj.veterinaria.excepciones.NotFoundExceptionCliente;
import puj.veterinaria.excepciones.NotFoundExceptionMascota;

@ControllerAdvice
public class ControladorError {
  
  @ExceptionHandler(NotFoundExceptionMascota.class)
  public String errorMascota(Model modelo, NotFoundExceptionMascota except) {
    modelo.addAttribute("mensajeError", except.obtenerMensaje());
    return "/html/error/pagina-error-mascota";
  }

  @ExceptionHandler(NotFoundExceptionCliente.class)
  public String errorCliente(Model modelo, NotFoundExceptionCliente except) {
    modelo.addAttribute("mensajeError", except.obtenerMensaje());
    return "html/error/pagina-error-cliente";
  }
}
