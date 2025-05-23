package puj.veterinaria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import puj.veterinaria.entidades.Administrador;
import puj.veterinaria.repositorios.RepositorioAdmin;

@Service
public class AdminServicio implements IAdminServicio {

    @Autowired
    RepositorioAdmin repositorioAdministrador;

    @Override
    public Administrador findById(Long id) {
        return repositorioAdministrador.findById(id).orElse(null);
    }

    @Override
    public List<Administrador> findAll() {
        return repositorioAdministrador.findAll();
    }

    @Override
    public Administrador findByCorreo(String correo) {
        return repositorioAdministrador.findByCorreo(correo);
    }

    @Override
    public Administrador findByUsername(String username) {
        return repositorioAdministrador.findByUsername(username);
    }

    @Override
    public void addAdministrador(Administrador admin) {
        repositorioAdministrador.save(admin);
    }

    @Transactional
    @Override
    public void updateAdministrador(Long id, Administrador nuevoAdmin) {
        Administrador admin = repositorioAdministrador.findById(id)
            .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));

        admin.setNombre(nuevoAdmin.getNombre());
        admin.setCorreo(nuevoAdmin.getCorreo());
        admin.setCelular(nuevoAdmin.getCelular());
        admin.setUsername(nuevoAdmin.getUsername());

        repositorioAdministrador.save(admin);
    }

    @Override
    public void updateAdministrador(Administrador admin) {
        repositorioAdministrador.save(admin);
    }

    @Override
    public void deleteById(Long id) {
        repositorioAdministrador.deleteById(id);
    }

    @Override
    public Long numeroAdministradores() {
        return repositorioAdministrador.count();
    }
}

