package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import puj.veterinaria.servicios.ITratamientoServicio;
import puj.veterinaria.servicios.IVeterinarioServicio;

@Controller
@RequestMapping("/veterinario")
public class ControladorVeterinario {

  @Autowired
  IVeterinarioServicio veterinarioServicio;

  @Autowired
  ITratamientoServicio tratamientoServicio;

// Metodos GetMapping

  // URL-1: http://localhost:8090/veterinario
  // URL-2: http://localhost:8090/veterinario/
  @GetMapping({"","/"})
  public String paginaPrincipal() {
    return "redirect:/veterinario/veterinarios";
  }

  @GetMapping("/dashboard")
  public String mostrarDashboard() {
    return "/html/veterinario/dashboard";
  }

  @GetMapping("/veterinarios")
  public String mostrarVeterinarios(Model modelo) {
    
    return "/html/veterinario/mostrar-veterinarios";
  }

// Metodos PostMapping

}
