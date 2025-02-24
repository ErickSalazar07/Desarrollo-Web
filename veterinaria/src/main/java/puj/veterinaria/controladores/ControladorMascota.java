package puj.veterinaria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mascota")
public class ControladorMascota {
  
  // URL: http://localhost:8090/mascota/mostrar-mascotas
  @GetMapping("/mostrar-mascotas")
  public String mostrarInfoMascotas(Model modelo) {
    return "html/mascota/mostrar-mascotas";
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/mostrar-mascota?id=1 
  @GetMapping("/mostrar-mascota")
  public String mostrarMascota(Model modelo, @RequestParam("id") Integer id) {
    return "html/mascota/mostrar-mascota";
  }
}
