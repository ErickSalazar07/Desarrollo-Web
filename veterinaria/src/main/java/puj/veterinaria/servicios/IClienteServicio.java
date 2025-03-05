package puj.veterinaria.servicios;

import java.util.Collection;

import puj.veterinaria.entidades.Cliente;

public interface IClienteServicio {
  public Cliente findById(Long id);
  public Collection<Cliente> findAll();
  public Cliente findByCorreoAndCedula(String correo, String cedula);
  public Cliente findByCedula(String cedula);
  public void addCliente(Cliente cliente);
  public Cliente updateCliente(Long id, Cliente cliente);
  public void deleteById(Long id);
}
