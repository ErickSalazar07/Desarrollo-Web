package puj.veterinaria.servicios.cliente;

import java.util.List;

import puj.veterinaria.entidades.Cliente;

public interface IClienteServicio {
  public Cliente findById(Long id);
  public Cliente findByCorreo (String correo);
  public List<Cliente> findAll();
  public Cliente findByCorreoAndCedula(String correo, String cedula);
  public Cliente findByCedula(String cedula);
  public Cliente addCliente(Cliente cliente);
  public Cliente updateCliente(Long id, Cliente cliente);
  public Cliente updateCliente(Cliente cliente);
  public void deleteById(Long id);
  public Long numeroClientes();
}
