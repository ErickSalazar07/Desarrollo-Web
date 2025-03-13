package puj.veterinaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.excepciones.NotFoundExceptionCliente;
import puj.veterinaria.servicios.ClienteServicio;
import puj.veterinaria.servicios.MascotaServicio;


@Controller
@RequestMapping("/cliente")
public class ControladorCliente {

  @Autowired
  ClienteServicio clienteServicio;
  @Autowired
  MascotaServicio mascotaServicio;

// Metodos GetMapping

  // URL-1: http://localhost:8090/cliente
  // URL-2: http://localhost:8090/cliente/
  @GetMapping({"","/"})
  public String paginaInicio() {
    return "redirect:/cliente/clientes";
  }

  @GetMapping("/dashboard")
  public String mostrarDashboard(){
    return "/html/cliente/dashboard_cliente";
  }
  
  public String getMethodName(@RequestParam String param) {
      return new String();
  }
  

  // URL: http://localhost:8090/cliente/clientes
  @GetMapping("/clientes")
  public String mostrarClientes(Model modelo) {
    modelo.addAttribute("clientes", clienteServicio.findAll());
    return "/html/cliente/mostrar-clientes";
  }

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/mostrar-cliente/1
  @GetMapping({"/mostrar/cliente/","/mostrar-cliente/{id}"})
  public String mostrarCliente(Model modelo, @PathVariable(value = "id", required = false) Long id) {
    Cliente cliente;

    if(id == null)
      throw new NotFoundExceptionCliente("Se necesita el ID para poder referenciar al cliente, verifique");

    cliente = clienteServicio.findById(id);
    if(cliente == null)
      throw new NotFoundExceptionCliente("El cliente con ID: " + id + " No existe");

    modelo.addAttribute("cliente", cliente);
    return "/html/cliente/mostrar-cliente";
  }

  // URL: http://localhost:8090/cliente/add
  @GetMapping("/add")
  public String mostrarFormularioCrear(Model modelo) {
    modelo.addAttribute("cliente", new Cliente());
    return "/html/cliente/crear-cliente";
  }

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/update/1
  @GetMapping("/update/{id}")
  public String mostrarFormularioActualizar(Model modelo, @PathVariable("id") Long id) {
    modelo.addAttribute("cliente", clienteServicio.findById(id));
    return "/html/cliente/actualizar-cliente";
  }

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/delete/1
  @GetMapping("/delete/{id}")
  public String eliminarCliente(@PathVariable("id") Long id) {
    clienteServicio.deleteById(id);
    return "redirect:/cliente/clientes";
  }

  @GetMapping("/login")
  public String loginCliente(Model modelo) {
    Cliente clienteIngresar = new Cliente();
    modelo.addAttribute("cliente", clienteIngresar);
    return "/html/cliente/login-cliente";
  }

// Metodos PostMapping

  @PostMapping("/agregar")
  public String agregarCliente(@ModelAttribute("cliente") Cliente cliente) {
    clienteServicio.addCliente(cliente);
    return "redirect:/cliente/clientes";
  }

  @PostMapping("/update/{id}")
  public String actualizarCliente(@ModelAttribute("cliente") Cliente cliente, @PathVariable("id") Long id) {
    clienteServicio.updateCliente(id,cliente);
    return "redirect:/cliente/clientes";
  }

  @PostMapping("/login")
  public String loginCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
    Cliente clienteBuscar = clienteServicio.findByCorreoAndCedula(cliente.getCorreo(), cliente.getCedula());
    if(clienteBuscar == null) {
      model.addAttribute("error", "Datos incorrectos");
      return "/html/cliente/login-cliente";
    }
    
    return "redirect:/cliente/mostrar-cliente/"+clienteBuscar.getId();
  }
} 
