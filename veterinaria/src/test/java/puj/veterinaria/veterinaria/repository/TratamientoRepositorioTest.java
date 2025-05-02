package puj.veterinaria.veterinaria.repository;

import org.junit.Test;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.entidades.DTO.TratamientoDTO;
import puj.veterinaria.repositorios.RepositorioTratamiento;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TratamientoRepositorioTest {

    @Autowired
    private RepositorioTratamiento repositorioTratamiento;

    // @Test
    // public void tratamientoRepositorio_cantidadTratamientosPorTipoMedicamento_long(){
    //     //arrange
    //     Droga drogaPrueba = new Droga("drogaPrueba", 1.0, 1.0, 3, 0);
    //     Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
    //     Mascota mascota1 = new Mascota("nuevoMascota", "Perro", 12, 12.0, "x", "foto", true);
        
    //     TratamientoDTO tratamientoDTO = new TratamientoDTO(12345L, "TratamientoPrueba", LocalDate.now(), Long drogaAsignadaID, Long mascotaID,
    //   "12343187634534");
    //     Tratamiento tratamiento1 = new Tratamiento();
    //     Tratamiento tratamiento2 = new Tratamiento();
    
    //     repositorioTratamiento.save(tratamiento1);
    //     //act
    //     long cantidadTratamientosPorTipoMedicamento = repositorioTratamiento.cantidadTratamientosPorTipoMedicamento("drogaPrueba");
    //     //assert
    //     Assertions.assertThat(cantidadTratamientosPorTipoMedicamento).isEqualTo(1);

    //}
    
}
