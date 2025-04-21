package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Administrador;

public interface IAdminServicio {

    public Administrador findById(Long id);
    public List<Administrador> findAll();
    public Administrador findByCorreo(String correo);
    public Administrador findByUsername(String username);
    public void addAdministrador(Administrador admin);
    public void updateAdministrador(Long id, Administrador admin);
    public void updateAdministrador(Administrador admin);
    public void deleteById(Long id);
    public Long numeroAdministradores();
}

