package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puj.veterinaria.entidades.Droga;

@Repository
public interface RepositorioDroga extends JpaRepository<Droga, Long> {
  
  public long count();

}
