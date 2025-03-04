package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Cliente;

public interface IClienteServicio {
  public Cliente findById(Integer id);
  public Collection<Cliente> findAll();
  public Cliente findByCorreoAndCedula(String correo, String cedula);
  public void addCliente(Cliente cliente);
  public void updateCliente(Cliente cliente);
  public void deleteById(Integer id);
}
