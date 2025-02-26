package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import puj.veterinaria.servicios.IMascotaServicio;

@Controller
@RequestMapping("/mascota")
public class ControladorMascota {
  
  @Autowired
  IMascotaServicio mascotaServicio; 

/*
  Metodos @GetMapping
*/

  // URL: http://localhost:8090/mascota/mostrar-mascotas
  @GetMapping("/mostrar-mascotas")
  public String mostrarInfoMascotas(Model modelo) {
    modelo.addAttribute("mascotas", mascotaServicio.searchAll());
    return "html/mascota/mostrar-mascotas";
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/mostrar-mascota?id=1 
  @GetMapping("/mostrar-mascota/{id}")
  public String mostrarMascota(Model modelo, @PathVariable("id") Integer id) {
    modelo.addAttribute("mascota", mascotaServicio.searchById(id));
    return "html/mascota/mostrar-mascota";
  }
}
