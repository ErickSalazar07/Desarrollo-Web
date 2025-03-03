package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.servicios.IMascotaServicio;

@Controller
@RequestMapping("/mascota")
public class ControladorMascota {
  
  @Autowired
  IMascotaServicio mascotaServicio; 

// Metodos @GetMapping

  @GetMapping("/")
  public String paginaInicioMascota() {
    return "redirect:/mascota/mascotas";
  }

  // URL: http://localhost:8090/mascota/mascotas
  @GetMapping("/mascotas")
  public String mostrarInfoMascotas(Model modelo) {
    modelo.addAttribute("mascotas", mascotaServicio.searchAll());
    return "html/mascota/mostrar-mascotas";
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/mostrar-mascota/1 
  @GetMapping("/mostrar-mascota/{id}")
  public String mostrarMascota(Model modelo, @PathVariable("id") Integer id) {
    modelo.addAttribute("mascota", mascotaServicio.searchById(id));
    return "html/mascota/mostrar-mascota";
  }

  // URL: http://localhost:8090/mascota/add
  @GetMapping("/add")
  public String mostrarFormularioCrear(Model modelo) {
    Mascota mascota = new Mascota(null,"","",null,null,"",null,null);

    modelo.addAttribute("mascota", mascota);

    return "/html/mascota/crear-mascota";
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/update/1
  @GetMapping("/update/{id}")
  public String mostrarFormularioActualizar(Model modelo, @PathVariable("id") Integer id) {
    modelo.addAttribute("mascota", mascotaServicio.searchById(id));
    return "html/mascota/actualizar-mascota";
  }

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/delete/1
  @GetMapping("/delete/{id}")
  public String eliminarMascota(@PathVariable("id") Integer id) {
    mascotaServicio.deleteById(id);
    return "redirect:/mascota/mascotas";
  }
  
// Metodos PostMapping

  @PostMapping("/agregar")
  public String agregarMascota(@ModelAttribute("mascota") Mascota mascota) {
    mascotaServicio.addMascota(mascota);
    return "redirect:/mascota/mascotas";
  }

  @PostMapping("/update/{id}")
  public String agregarMascota(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") int id) {
    mascotaServicio.updateMascota(mascota);
    return "redirect:/mascota/mascotas";
  }
}
