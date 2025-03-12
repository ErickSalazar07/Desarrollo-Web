package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {

  public Cliente findByCorreoAndCedula(String correo, String cedula);
  public Cliente findByCedula(String cedula);
  public long count();

}
