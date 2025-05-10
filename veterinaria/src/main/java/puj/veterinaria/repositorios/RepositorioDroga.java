package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Droga;

@Repository
public interface RepositorioDroga extends JpaRepository<Droga, Long> {
  
  public long count();
  
  @Query("SELECT SUM(d.unidadVendida*d.precioVenta) FROM Droga d")
  public double totalVentas();

  @Query("SELECT SUM(d.unidadVendida*d.precioVenta - d.unidadDisponible*d.precioCompra) FROM Droga d")
  public double totalGanancias();

}