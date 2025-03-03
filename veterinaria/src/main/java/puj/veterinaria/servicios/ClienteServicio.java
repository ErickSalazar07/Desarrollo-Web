package puj.veterinaria.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.repositorios.RepositorioCliente;

@Service
public class ClienteServicio implements IClienteServicio {
  @Autowired
  RepositorioCliente repositorioCliente;

  @Override
  public Cliente findById(Integer id) {
    return repositorioCliente.findById(id);
  }

  @Override
  public Collection<Cliente> findAll() {
    return repositorioCliente.findAll();
  }

  @Override
  public void addCliente(Cliente cliente) {
    repositorioCliente.addCliente(cliente);
  }

  @Override
  public void updateCliente(Cliente cliente) {
    repositorioCliente.updateCliente(cliente);
  }

  @Override
  public void deleteById(Integer id) {
    repositorioCliente.deleteById(id);
  }
}
