package puj.veterinaria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.repositorios.RepositorioCliente;

@Service
public class ClienteServicio implements IClienteServicio {
  @Autowired
  RepositorioCliente repositorioCliente;

  @Override
  public Cliente findById(Long id) {
    return repositorioCliente.findById(id).orElse(null);
  }

  @Override
  public List<Cliente> findAll() {
    return repositorioCliente.findAll();
  }

  @Override
  public Cliente findByCorreoAndCedula(String correo, String cedula) {
    return repositorioCliente.findByCorreoAndCedula(correo, cedula);
  }

  @Override
  public Cliente findByCedula(String cedula) {
    return repositorioCliente.findByCedula(cedula);
  }

  @Override
  public void addCliente(Cliente cliente) {
    repositorioCliente.save(cliente);
  }

  @Transactional
  @Override
  public void updateCliente(Long id, Cliente nuevoCliente) {
    Cliente cliente = repositorioCliente.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

    cliente.setNombre(nuevoCliente.getNombre());
    cliente.setCorreo(nuevoCliente.getCorreo());
    cliente.setCelular(nuevoCliente.getCelular());

    repositorioCliente.save(cliente);
  }

  @Override
  public void updateCliente(Cliente cliente) {
    repositorioCliente.save(cliente);
  }

  @Override
  public void deleteById(Long id) {
    repositorioCliente.deleteById(id);
  }

  @Override
  public Long numeroClientes() {
    return repositorioCliente.count();
  }
}
