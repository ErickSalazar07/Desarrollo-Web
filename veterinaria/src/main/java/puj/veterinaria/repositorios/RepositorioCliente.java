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
    3145194072L, Arrays.asList(new Mascota(1, "Pachini"),
    new Mascota(2, "Zeus"))));
    baseDatosCliente.put(2, new Cliente(2,"19812305","Pedro","pedro@email.com",
    3045591094L, Arrays.asList(new Mascota(3, "Figaro"),
    new Mascota(4, "Lola"))));
    baseDatosCliente.put(3, new Cliente(3,"27082133","Luis","luis@email.com",
    3944193373L,null));
    baseDatosCliente.put(4, new Cliente(4,"17190115","Juliana","juli@email.com",
    3305004013L,null));
  }

  public Cliente findById(Integer id) {
    return baseDatosCliente.get(id);
  }

  public Collection<Cliente> findAll() {
    return baseDatosCliente.values();
  }

  public void addCliente(Cliente cliente) {
    Integer idDisponible = baseDatosCliente.get(baseDatosCliente.size()).getId()+1;
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
