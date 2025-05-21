package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import puj.veterinaria.entidades.Administrador;

public interface RepositorioAdmin extends JpaRepository<Administrador, Long> {

  Administrador findByCorreo(String correo);

  Administrador findByUsername(String username);
  
  //@Query("SELECT SUM(d.unidadVendida*d.precioVenta - d.unidadDisponible*d.precioCompra) FROM Droga d")?1
  // String encontrarRol(String username, String passwEnc);

}
