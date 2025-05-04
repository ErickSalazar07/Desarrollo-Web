package puj.veterinaria.veterinaria.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;

@DataJpaTest
public class MascotaRepositorioTest {
  
  @Autowired
  private RepositorioMascota repositorioMascota;

  @Autowired
  private RepositorioCliente repositorioCliente;

  @BeforeEach
  public void init() {
    repositorioCliente.save(new Cliente("123","cli1","cli1@noemail","1234"));
    repositorioCliente.save(new Cliente("234","cli2","cli2@noemail","2345"));
    repositorioCliente.save(new Cliente("345","cli3","cli3@noemail","3456"));

    Mascota mascota = new Mascota("m1","p1",4,5.0,null,"f1",false);
    mascota.setCliente(repositorioCliente.findById(1L).orElse(null));
    repositorioMascota.save(mascota);
    mascota = new Mascota("m2","p2",5,7.0,"e2","f2",true);
    mascota.setCliente(repositorioCliente.findById(2L).orElse(null));
    repositorioMascota.save(mascota);
  }

// C (create)

  @Test
  public void mascotaRepositorio_addMascota_Mascota() {
    
    // Arrange
    Mascota mascotaAgregar = new Mascota("m3","p3",4,5.0,"e3","f3",true);
    
    // Act
    mascotaAgregar.setCliente(repositorioCliente.save(new Cliente("234","cli4","cli4@noemail","2345")));
    Mascota mascotaVerificar = repositorioMascota.save(mascotaAgregar);

    // Assert
    Assertions.assertThat(mascotaVerificar).isNotNull();
    Assertions.assertThat(mascotaVerificar.getNombre()).isEqualTo("m3");
  }

// R (read)

  @Test
  public void mascotaRepositorio_findById_Mascota() {

    // Arrange
    // Act
    Mascota mascotaVerificar = repositorioMascota.findById(1L).orElse(null);

    // Assert
    Assertions.assertThat(mascotaVerificar.getId()).isNotNull();
    Assertions.assertThat(mascotaVerificar.getId()).isEqualTo(1L);
    Assertions.assertThat(mascotaVerificar.getNombre()).isEqualTo("m1");
    Assertions.assertThat(mascotaVerificar.getRaza()).isEqualTo("p1");
  }

  @Test
  public void mascotaRepositorio_findAll_Mascotas() {
    
    // Arrange
    // Act
    List<Mascota> mascotasVerificar = repositorioMascota.findAll();

    // Assert
    Assertions.assertThat(mascotasVerificar).isNotNull();
    Assertions.assertThat(mascotasVerificar.size()).isGreaterThan(0);
    Assertions.assertThat(mascotasVerificar.size()).isEqualTo(2);
  }

// U (update)

  @Test
  public void mascotaRepositorio_updateMascota_Mascota() {

    // Arrange
    Mascota mascotaModificar = repositorioMascota.findById(2L).orElse(null);

    // Act
    mascotaModificar.setEnfermedad("modificado");
    Mascota mascotaVerificar = repositorioMascota.save(mascotaModificar);

    // Assert
    Assertions.assertThat(mascotaVerificar).isNotNull();
    Assertions.assertThat(mascotaVerificar.getEnfermedad()).isEqualTo("modificado");
    Assertions.assertThat(mascotaVerificar.getId()).isEqualTo(2L);
  }

// D (delete)

  @Test
  public void mascotaRepositorio_deleteById_Mascota() {

    // Arrange
    Long idMascotaEliminar = 1L;
    Mascota mascotaVerificar = null;

    // Act
    repositorioMascota.deleteById(idMascotaEliminar);
    mascotaVerificar = repositorioMascota.findById(idMascotaEliminar).orElse(null);

    // Assert
    Assertions.assertThat(mascotaVerificar).isNull();
  }

}
