package puj.veterinaria.servicios.administrador;

import java.util.List;

import puj.veterinaria.entidades.Administrador;

public interface IAdminServicio {

  public Administrador findById(Long id);
  public List<Administrador> findAll();
  public Administrador findByCorreo(String correo);
  public Administrador findByUsername(String username);
  public Administrador addAdministrador(Administrador admin);
  public Administrador updateAdministrador(Long id, Administrador admin);
  public Administrador updateAdministrador(Administrador admin);
  public void deleteById(Long id);
  public Long numeroAdministradores();


}

