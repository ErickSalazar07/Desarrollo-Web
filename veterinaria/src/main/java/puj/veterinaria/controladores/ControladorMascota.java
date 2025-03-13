package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.excepciones.NotFoundExceptionCliente;
import puj.veterinaria.excepciones.NotFoundExceptionMascota;
import puj.veterinaria.servicios.IClienteServicio;
import puj.veterinaria.servicios.IMascotaServicio;

@Controller
@RequestMapping("/mascota")
public class ControladorMascota {
  
  @Autowired
  IMascotaServicio mascotaServicio; 

  @Autowired
  IClienteServicio clienteServicio;

// Metodos @GetMapping

  // URL-1: http://localhost:8090/mascota
  // URL-2: http://localhost:8090/mascota/
  @GetMapping({"","/"})
  public String paginaInicio() {
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
  @GetMapping({"/mostrar-mascota/","/mostrar-mascota/{id}"})
  public String mostrarMascota(Model modelo, @PathVariable(value = "id", required = false) Long id) {
    Mascota mascota;

    if(id == null)
      throw new NotFoundExceptionMascota("El id es necesario para referenciar a la mascota, verifique");

    mascota = mascotaServicio.searchById(id);
    if(mascota == null) 
      throw new NotFoundExceptionMascota(id);

    modelo.addAttribute("mascota", mascota);
    return "html/mascota/mostrar-mascota";
  }

  // URL: http://localhost:8090/mascota/add
  @GetMapping("/add")
  public String mostrarFormularioCrear(Model modelo) {
    Mascota mascota = new Mascota();

    modelo.addAttribute("mascota", mascota);

    return "/html/mascota/crear-mascota";
  }
  
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/update/1
  @GetMapping("/update/{id}")
  public String mostrarFormularioActualizar(Model modelo, @PathVariable("id") Long id) {
    modelo.addAttribute("mascota", mascotaServicio.searchById(id));
    return "html/mascota/actualizar-mascota";
  }

  @GetMapping("/cambiar-estado/{id}")
  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/cambiar-estado/1
  public String cambiarEstado(@PathVariable("id") Long id) {
    mascotaServicio.cambiarEstadoById(id);
    return "redirect:/mascota/mascotas";
  }

  // ? Cambiar id por algun id dentro de los animales guardados en el repositorio
  // URL: http://localhost:8090/mascota/delete/1
  @GetMapping("/delete/{id}")
  public String eliminarMascota(@PathVariable("id") Long id) {
    mascotaServicio.deleteById(id);
    return "redirect:/mascota/mascotas";
  }
  
// Metodos PostMapping

  @PostMapping("/agregar")
  public String agregarMascota(@ModelAttribute("mascota") Mascota mascota) {
    // Buscar el cliente antes de asignarlo a la mascota
    if (mascota.getCliente() != null && mascota.getCliente().getCedula() != null) {
      Cliente clienteExistente = clienteServicio.findByCedula(mascota.getCliente().getCedula());
      if (clienteExistente == null) 
        throw new NotFoundExceptionCliente("No se encontro el cliente con cedula: "+mascota.getCliente().getCedula());
        // Manejar el caso en que el cliente no exista
      mascota.setCliente(clienteExistente);
    }
    
    mascotaServicio.addMascota(mascota);
    return "redirect:/mascota/mascotas";
  }

  @PostMapping("/update/{id}")
  public String actualizarMascota(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") Long id) {

    if(clienteServicio.findByCedula(mascota.getCliente().getCedula()) == null) 
      throw new NotFoundExceptionCliente("No se encontro el cliente con cedula: "+mascota.getCliente().getCedula());

    mascotaServicio.updateMascota(id,mascota);
    return "redirect:/mascota/mascotas";
  }
}
