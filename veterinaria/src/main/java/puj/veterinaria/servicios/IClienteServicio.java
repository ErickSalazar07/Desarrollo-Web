package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Cliente;

public interface IClienteServicio {
  public Cliente findById(Long id);
  public List<Cliente> findAll();
  public Cliente findByCorreoAndCedula(String correo, String cedula);
  public Cliente findByCedula(String cedula);
  public void addCliente(Cliente cliente);
  public void updateCliente(Long id, Cliente cliente);
  public void updateCliente(Cliente cliente);
  public void deleteById(Long id);
  public Long numeroClientes();
}
