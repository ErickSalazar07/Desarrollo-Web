package puj.veterinaria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.servicios.ClienteServicio;


@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {

  @Autowired
  ClienteServicio clienteServicio;

// Metodos PostMapping

  // URL: http://localhost:8090/cliente/add
  @PostMapping("/add")
  @Operation(summary = "Agrega un nuevo Cliente, pasado por el body.")
  public void agregarCliente(@RequestBody Cliente cliente) {
    cliente.setId(null);
    clienteServicio.addCliente(cliente);
  }

// Metodos GetMapping

  // URL: http://localhost:8090/cliente/clientes
  @GetMapping("/clientes")
  @Operation(summary = "Retorna todos los Clientes de la bd.")
  public List<Cliente> obtenerClientes() {
    return clienteServicio.findAll();
  }

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/get-cliente/1
  @GetMapping("/get-cliente/{id}")
  @Operation(summary = "Retorna 1 Cliente, el cual tiene el id, que se especifica.")
  public Cliente obtenerCliente(@PathVariable(value = "id") Long id) {
    return clienteServicio.findById(id);
  }

  @GetMapping("/get-cliente-cedula/{cedula}")
  @Operation(summary = "Retorna 1 Cliente, correspondiente a la cedula.")
  public Cliente obtenerClientePorCedula(@PathVariable(value = "cedula") String cedula) {
    return clienteServicio.findByCedula(cedula);
  }

// Metodos PutMapping

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/update/1
  @PutMapping("/update/{id}")
  @Operation(summary = "Actualiza un Cliente existente en la base de datos.")
  public void actualizarCliente(@RequestBody Cliente cliente) {
    Cliente clienteActualizar = clienteServicio.findById(cliente.getId());

    clienteActualizar.setNombre(cliente.getNombre());
    clienteActualizar.setCorreo(cliente.getCorreo());
    clienteActualizar.setCelular(cliente.getCelular());

    clienteServicio.updateCliente(clienteActualizar);
  }

// Metodos DeleteMapping

  // ? Cambiar id por algun id dentro de los clientes guardados en el repositorio
  // URL: http://localhost:8090/cliente/delete/1
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Elimina un Cliente, el cual corresponde al id que se especifica.")
  public void eliminarCliente(@PathVariable("id") Long id) {
    clienteServicio.deleteById(id);
  }
} 
