package puj.veterinaria.veterinaria.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.entidades.DTO.MascotaDTO;
import puj.veterinaria.entidades.DTO.TratamientoDTO;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioDroga;
import puj.veterinaria.repositorios.RepositorioMascota;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TratamientoRepositorioTest {

    @Autowired
    private RepositorioTratamiento repositorioTratamiento;

    @Autowired
    private RepositorioMascota repositorioMascota;

    @Autowired
    private RepositorioVeterinario repositorioVeterinario;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioDroga repositorioDroga;

    @BeforeEach
    public void setup(){
        Droga drogaPrueba1 = new Droga("drogaPrueba1", 1.0, 1.0, 3, 0);
        Droga drogaPrueba2 = new Droga("drogaPrueba2", 1.0, 1.0, 3, 0);
        repositorioDroga.save(drogaPrueba1);        
        repositorioDroga.save(drogaPrueba2);   

        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        repositorioVeterinario.save(veterinario1);

        //cReando instancia de mascota con su respectivo dueño
        Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");
        repositorioCliente.save(cliente);
        MascotaDTO mascotaDTO1 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota1 = new Mascota(mascotaDTO1);
        mascota1.setCliente(cliente);  
        repositorioMascota.save(mascota1);

        TratamientoDTO tratamientoDTO1 = new TratamientoDTO(-1L, "TratamientoPrueba1", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO2 = new TratamientoDTO(-1L, "TratamientoPrueba2", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO3 = new TratamientoDTO(-1L, "TratamientoPrueba3", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO4 = new TratamientoDTO(-1L, "TratamientoPrueba4", LocalDate.now(), null, null, null);

        Tratamiento tratamiento1_droga1 = new Tratamiento(tratamientoDTO1);
        tratamiento1_droga1.setDrogaAsignada(drogaPrueba1);
        tratamiento1_droga1.setVeterinarioEncargado(veterinario1);
        tratamiento1_droga1.setMascota(mascota1);

        Tratamiento tratamiento2_droga2 = new Tratamiento(tratamientoDTO2);
        tratamiento2_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento2_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento2_droga2.setMascota(mascota1);

        Tratamiento tratamiento3_droga2 = new Tratamiento(tratamientoDTO3);
        tratamiento3_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento3_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento3_droga2.setMascota(mascota1);

        Tratamiento tratamiento4_droga2 = new Tratamiento(tratamientoDTO4);
        tratamiento4_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento4_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento4_droga2.setMascota(mascota1);
    
        repositorioTratamiento.save(tratamiento1_droga1);
        repositorioTratamiento.save(tratamiento2_droga2);
        repositorioTratamiento.save(tratamiento3_droga2);
        repositorioTratamiento.save(tratamiento4_droga2);
    }
    @Test
    public void tratamientoRepositorio_cantidadTratamientosPorTipoMedicamento_long(){
        //arrange
        Droga drogaPrueba1 = new Droga("drogaPrueba1", 1.0, 1.0, 3, 0);
        Droga drogaPrueba2 = new Droga("drogaPrueba2", 1.0, 1.0, 3, 0);
        repositorioDroga.save(drogaPrueba1);        
        repositorioDroga.save(drogaPrueba2);   

        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        repositorioVeterinario.save(veterinario1);

        //cReando instancia de mascota con su respectivo dueño
        Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");
        repositorioCliente.save(cliente);
        MascotaDTO mascotaDTO1 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota1 = new Mascota(mascotaDTO1);
        mascota1.setCliente(cliente);  
        repositorioMascota.save(mascota1);

        TratamientoDTO tratamientoDTO1 = new TratamientoDTO(-1L, "TratamientoPrueba1", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO2 = new TratamientoDTO(-1L, "TratamientoPrueba2", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO3 = new TratamientoDTO(-1L, "TratamientoPrueba3", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO4 = new TratamientoDTO(-1L, "TratamientoPrueba4", LocalDate.now(), null, null, null);

        Tratamiento tratamiento1_droga1 = new Tratamiento(tratamientoDTO1);
        tratamiento1_droga1.setDrogaAsignada(drogaPrueba1);
        tratamiento1_droga1.setVeterinarioEncargado(veterinario1);
        tratamiento1_droga1.setMascota(mascota1);

        Tratamiento tratamiento2_droga2 = new Tratamiento(tratamientoDTO2);
        tratamiento2_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento2_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento2_droga2.setMascota(mascota1);

        Tratamiento tratamiento3_droga2 = new Tratamiento(tratamientoDTO3);
        tratamiento3_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento3_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento3_droga2.setMascota(mascota1);

        Tratamiento tratamiento4_droga2 = new Tratamiento(tratamientoDTO4);
        tratamiento4_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento4_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento4_droga2.setMascota(mascota1);
    
        repositorioTratamiento.save(tratamiento1_droga1);
        repositorioTratamiento.save(tratamiento2_droga2);
        repositorioTratamiento.save(tratamiento3_droga2);
        repositorioTratamiento.save(tratamiento4_droga2);
        //act
        long cantidadTratamientos_droga1 = repositorioTratamiento.cantidadTratamientosPorTipoMedicamento("drogaPrueba1");
        long cantidadTratamientos_droga2 = repositorioTratamiento.cantidadTratamientosPorTipoMedicamento("drogaPrueba2");

        //assert
        Assertions.assertThat(cantidadTratamientos_droga1).isEqualTo(1);
        Assertions.assertThat(cantidadTratamientos_droga2).isEqualTo(3);

    }
    @Test
    public void tratamientoRepositorio_top3TratamientosMasUnidadesVendidas_listTratamiento(){
        Droga drogaPrueba1 = new Droga("drogaPrueba1", 1.0, 1.0, 3, 0);
        Droga drogaPrueba2 = new Droga("drogaPrueba2", 1.0, 1.0, 3, 0);
        repositorioDroga.save(drogaPrueba1);        
        repositorioDroga.save(drogaPrueba2);   

        Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234","nuevaEspecialidad", "foto", true);
        repositorioVeterinario.save(veterinario1);

        //cReando instancia de mascota con su respectivo dueño
        Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");
        repositorioCliente.save(cliente);
        MascotaDTO mascotaDTO1 = new MascotaDTO(-1L,"nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
        Mascota mascota1 = new Mascota(mascotaDTO1);
        mascota1.setCliente(cliente);  
        repositorioMascota.save(mascota1);

        TratamientoDTO tratamientoDTO1 = new TratamientoDTO(-1L, "TratamientoPrueba1", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO2 = new TratamientoDTO(-1L, "TratamientoPrueba2", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO3 = new TratamientoDTO(-1L, "TratamientoPrueba3", LocalDate.now(), null, null, null);
        TratamientoDTO tratamientoDTO4 = new TratamientoDTO(-1L, "TratamientoPrueba4", LocalDate.now(), null, null, null);

        Tratamiento tratamiento1_droga1 = new Tratamiento(tratamientoDTO1);
        tratamiento1_droga1.setDrogaAsignada(drogaPrueba1);
        tratamiento1_droga1.setVeterinarioEncargado(veterinario1);
        tratamiento1_droga1.setMascota(mascota1);

        Tratamiento tratamiento2_droga2 = new Tratamiento(tratamientoDTO2);
        tratamiento2_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento2_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento2_droga2.setMascota(mascota1);

        Tratamiento tratamiento3_droga2 = new Tratamiento(tratamientoDTO3);
        tratamiento3_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento3_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento3_droga2.setMascota(mascota1);

        Tratamiento tratamiento4_droga2 = new Tratamiento(tratamientoDTO4);
        tratamiento4_droga2.setDrogaAsignada(drogaPrueba2);
        tratamiento4_droga2.setVeterinarioEncargado(veterinario1);
        tratamiento4_droga2.setMascota(mascota1);
    
        repositorioTratamiento.save(tratamiento1_droga1);
        repositorioTratamiento.save(tratamiento2_droga2);
        repositorioTratamiento.save(tratamiento3_droga2);
        repositorioTratamiento.save(tratamiento4_droga2);

        //act
        List<Tratamiento> top3TratamientosMasUnidadesVendidas = repositorioTratamiento.top3TratamientosMasUnidadesVendidas(PageRequest.of(0,3)).orElse(null);
        //assert
        Assertions.assertThat(top3TratamientosMasUnidadesVendidas.size()).isEqualTo(3L);
        Assertions.assertThat(top3TratamientosMasUnidadesVendidas).allMatch(t -> t instanceof Tratamiento);
  }
    
}
