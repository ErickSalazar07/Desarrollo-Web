package puj.veterinaria.repositorios;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Mascota;

@Repository
public class RepositorioCliente {
  private Map<Integer,Cliente> baseDatosCliente;

  public RepositorioCliente() {
    baseDatosCliente = new HashMap<>();

    baseDatosCliente.put(1, new Cliente(1,"18872435","Juan","juan@email.com",
    "3145194072", Arrays.asList(new Mascota(1,null,null,null,null,null,null,null),
    new Mascota(2,null,null,null,null,null,null,null))));
    baseDatosCliente.put(2, new Cliente(2,"19812305","Pedro","pedro@email.com",
    "3045591094", Arrays.asList(new Mascota(3,null,null,null,null,null,null,null),
    new Mascota(4,null,null,null,null,null,null,null))));
    baseDatosCliente.put(3, new Cliente(3,"27082133","Luis","luis@email.com",
    "3944193373",null));
    baseDatosCliente.put(4, new Cliente(4,"17190115","Juliana","juli@email.com",
    "3305004013",null));
  }

  public Cliente findById(Integer id) {
    return baseDatosCliente.get(id);
  }

  public Collection<Cliente> findAll() {
    return baseDatosCliente.values();
  }

  public Cliente findByCorreoAndCedula(String correo, String cedula) {
    for(Cliente cliente: baseDatosCliente.values()) 
      if(cliente.getCorreo().equals(correo) && cliente.getCedula().equals(cedula)) 
        return cliente;
    return null;
  }

  public void addCliente(Cliente cliente) {
    Integer idDisponible = baseDatosCliente.keySet().stream().max(Integer::compareTo).orElse(0)+1;
    cliente.setId(idDisponible);
    baseDatosCliente.put(idDisponible,cliente);
  }

  public void updateCliente(Cliente cliente) {
    baseDatosCliente.put(cliente.getId(),cliente);
  }

  public void deleteById(Integer id) {
    baseDatosCliente.remove(id);
  }
}
