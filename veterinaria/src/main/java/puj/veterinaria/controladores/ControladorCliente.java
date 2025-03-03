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
import puj.veterinaria.servicios.ClienteServicio;

@Controller
@RequestMapping("/cliente")
public class ControladorCliente {

  @Autowired
  ClienteServicio clienteServicio;

// Metodos GetMapping

  @GetMapping("/")
  public String paginaInicioCliente() {
    return "redirect:/cliente/clientes";
  }

  @GetMapping("/clientes")
  public String mostrarClientes(Model modelo) {
    modelo.addAttribute("clientes", clienteServicio.findAll());
    return "/html/cliente/mostrar-clientes";
  }

  @GetMapping("/mostrar-cliente/{id}")
  public String mostrarCliente(Model modelo, @PathVariable("id") Integer id) {
    modelo.addAttribute("cliente", clienteServicio.findById(id));
    return "/html/cliente/mostrar-cliente";
  }

  @GetMapping("/add")
  public String mostrarFormularioCrear(Model modelo) {
    Cliente cliente = new Cliente(null, null, null, null, null, null);
    modelo.addAttribute("cliente", cliente);
    return "/html/cliente/crear-cliente";
  }

  @GetMapping("/update/{id}")
  public String mostrarFormularioActualizar(Model modelo, @PathVariable("id") Integer id) {
    modelo.addAttribute("cliente", clienteServicio.findById(id));
    return "/html/cliente/actualizar-cliente";
  }

  @GetMapping("/delete/{id}")
  public String eliminarCliente(@PathVariable("id") Integer id) {
    clienteServicio.deleteById(id);
    return "redirect:/cliente/clientes";
  }

// Metodos PostMapping

  @PostMapping("/agregar")
  public String agregarCliente(@ModelAttribute("cliente") Cliente cliente) {
    clienteServicio.addCliente(cliente);
    return "redirect:/cliente/clientes";
  }

  @PostMapping("update/{id}")
  public String actualizarCliente(@ModelAttribute("cliente") Cliente cliente, @PathVariable("id") Integer id) {
    clienteServicio.updateCliente(cliente);
    return "redirect:/cliente/clientes";
  }
}
