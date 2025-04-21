package puj.veterinaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import puj.veterinaria.entidades.Administrador;

public interface RepositorioAdmin extends JpaRepository<Administrador, Long> {
    Administrador findByCorreo(String correo);
    Administrador findByUsername(String username);
}
