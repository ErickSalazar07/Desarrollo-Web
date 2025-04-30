package puj.veterinaria.veterinaria.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@DataJpaTest
@RunWith(SpringRunner.class)
public class VeterinarioRepositorioTest {
    
    @Autowired
    private RepositorioVeterinario repositorioVeterinario;

    @BeforeEach
    public void init(){
        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        Veterinario veterinario2 = new Veterinario("12343256354", "nuevoVeterinario2", "1234","nuevaEspecialidad2", "foto2", true);
        Veterinario veterinario3 = new Veterinario("1234333563456", "nuevoVeterinario3", "1234","nuevaEspecialidad3", "foto3", true);
        repositorioVeterinario.save(veterinario1);
        repositorioVeterinario.save(veterinario2);
        repositorioVeterinario.save(veterinario3);
    }
    

    //PRUEBAS CRUD VETERINARIO ----------------------------------------------------------------
    //Prueba C (Create)
    @Test
    public void veterinarioRepositorio_save_veterinario(){
        //Arrange
        Veterinario veterinario = new Veterinario("123431777", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        //Act
        Veterinario veterinarioGuardado =repositorioVeterinario.save(veterinario);
        //Assert
        Assertions.assertThat(veterinarioGuardado).isNotNull();
    }
    //Prueba R (Read)
    @Test
    public void veterinarioRepositorio_findAll_veterinarios(){
        //Arrange
        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        Veterinario veterinario2 = new Veterinario("12343256354", "nuevoVeterinario2", "1234","nuevaEspecialidad2", "foto2", true);
        Veterinario veterinario3 = new Veterinario("1234333563456", "nuevoVeterinario3", "1234","nuevaEspecialidad3", "foto3", true);
        repositorioVeterinario.save(veterinario1);
        repositorioVeterinario.save(veterinario2);
        repositorioVeterinario.save(veterinario3);
        //Act
        List<Veterinario> veterinarios = repositorioVeterinario.findAll();
        //Assert
        Assertions.assertThat(veterinarios).isNotNull();
        Assertions.assertThat(veterinarios.size()).isEqualTo(3);
    }
    
    //Prueba U (Update)
    @Test
    public void veterinarioRepositorio_update_veterinario(){
        //Arrange
        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        repositorioVeterinario.save(veterinario1);

        //Act
        veterinario1.setActivo(false);
        Veterinario veterinarioGuardado = repositorioVeterinario.save(veterinario1);
        //Assert
        Assertions.assertThat(veterinarioGuardado.getActivo()).isEqualTo(false);
    }

    //Prueba D (Delete)
    @Test
    public void veterinarioRepositorio_delete_EmptyVeterinario(){
        //Arrange
        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        Veterinario veterinario2 = new Veterinario("12343256354", "nuevoVeterinario2", "1234","nuevaEspecialidad2", "foto2", true);
        Veterinario veterinario3 = new Veterinario("1234333563456", "nuevoVeterinario3", "1234","nuevaEspecialidad3", "foto3", true);
        repositorioVeterinario.save(veterinario1);
        repositorioVeterinario.save(veterinario2);
        repositorioVeterinario.save(veterinario3);
        //Act
        repositorioVeterinario.delete(veterinario1);
        repositorioVeterinario.delete(veterinario2);
        repositorioVeterinario.delete(veterinario3);

        //Assert
        List<Veterinario> veterinarios = repositorioVeterinario.findAll();
        Assertions.assertThat(veterinarios).isEmpty();
    }

    //PRUEBAS QUERYS-----------------------------------------------------------------------------------------------
    
}
