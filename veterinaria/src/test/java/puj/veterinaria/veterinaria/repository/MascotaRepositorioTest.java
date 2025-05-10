package puj.veterinaria.veterinaria.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.DTO.MascotaDTO;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MascotaRepositorioTest {

    @Autowired
    private RepositorioMascota repositorioMascota;

    @Autowired
    private RepositorioCliente repositorioCliente;
    
    @BeforeEach
    public void setup(){
        Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");

        MascotaDTO mascotaDTO1 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota1 = new Mascota(mascotaDTO1);
        mascota1.setCliente(cliente);

        MascotaDTO mascotaDTO2 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota2 = new Mascota(mascotaDTO2);
        mascota2.setCliente(cliente);

        MascotaDTO mascotaDTO3 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", true, "12341234");
        Mascota mascota3 = new Mascota(mascotaDTO3);
        mascota3.setCliente(cliente);

        repositorioCliente.save(cliente);
        repositorioMascota.save(mascota1);
        repositorioMascota.save(mascota2);
        repositorioMascota.save(mascota3);
    }
    //TEST QUERYS ------------------------------------------------------
    @Test
    public void mascotaRepositorio_cantidadMascotasActivas_long(){
        //Arrange
        Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");

        MascotaDTO mascotaDTO1 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota1 = new Mascota(mascotaDTO1);
        mascota1.setCliente(cliente);

        MascotaDTO mascotaDTO2 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota2 = new Mascota(mascotaDTO2);
        mascota2.setCliente(cliente);

        MascotaDTO mascotaDTO3 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", true, "12341234");
        Mascota mascota3 = new Mascota(mascotaDTO3);
        mascota3.setCliente(cliente);

        repositorioCliente.save(cliente);
        repositorioMascota.save(mascota1);
        repositorioMascota.save(mascota2);
        repositorioMascota.save(mascota3);

        //Act
        Long cantidadMascotasActivas = repositorioMascota.cantidadMascotasActivas();
        //Assert
        Assertions.assertThat(cantidadMascotasActivas).isEqualTo(1);
    }
}
