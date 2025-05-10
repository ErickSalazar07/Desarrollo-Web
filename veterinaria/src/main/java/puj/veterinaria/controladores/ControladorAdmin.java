package puj.veterinaria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import puj.veterinaria.entidades.Administrador;
import puj.veterinaria.servicios.AdminServicio;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAdmin {

    @Autowired
    AdminServicio administradorServicio;

    // POST
    // URL: http://localhost:8090/admin/add
    @PostMapping("/add")
    @Operation(summary = "Agrega un nuevo Administrador pasado por el body.")
    public void agregarAdministrador(@RequestBody Administrador administrador) {
        administrador.setId(null);
        administradorServicio.addAdministrador(administrador);
    }

    // GET
    // URL: http://localhost:8090/admin/admins
    @GetMapping("/admins")
    @Operation(summary = "Retorna todos los Administradores de la base de datos.")
    public List<Administrador> obtenerAdministradores() {
        return administradorServicio.findAll();
    }

    // URL: http://localhost:8090/admin/get-admin/1
    @GetMapping("/get-admin/{id}")
    @Operation(summary = "Retorna un Administrador por su ID.")
    public Administrador obtenerAdministradorPorId(@PathVariable("id") Long id) {
        return administradorServicio.findById(id);
    }

    // URL: http://localhost:8090/admin/get-admin-correo/admin@mail.com
    @GetMapping("/get-admin-correo/{correo}")
    @Operation(summary = "Retorna un Administrador por su correo.")
    public Administrador obtenerAdministradorPorCorreo(@PathVariable("correo") String correo) {
        return administradorServicio.findByCorreo(correo);
    }

    // URL: http://localhost:8090/admin/get-admin-username/admin123
    @GetMapping("/get-admin-username/{username}")
    @Operation(summary = "Retorna un Administrador por su username.")
    public Administrador obtenerAdministradorPorUsername(@PathVariable("username") String username) {
        return administradorServicio.findByUsername(username);
    }

    // PUT
    // URL: http://localhost:8090/admin/update/1
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza un Administrador existente en la base de datos.")
    public void actualizarAdministrador(@RequestBody Administrador admin) {
        Administrador adminActualizar = administradorServicio.findById(admin.getId());

        adminActualizar.setNombre(admin.getNombre());
        adminActualizar.setCorreo(admin.getCorreo());
        adminActualizar.setCelular(admin.getCelular());
        adminActualizar.setUsername(admin.getUsername());

        administradorServicio.updateAdministrador(adminActualizar);
    }

    // DELETE
    // URL: http://localhost:8090/admin/delete/1
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina un Administrador según su ID.")
    public void eliminarAdministrador(@PathVariable("id") Long id) {
        administradorServicio.deleteById(id);
    }
}

