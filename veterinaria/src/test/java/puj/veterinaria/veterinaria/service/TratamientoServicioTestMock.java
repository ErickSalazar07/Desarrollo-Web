package puj.veterinaria.veterinaria.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.servicios.TratamientoServicio;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TratamientoServicioTestMock {
  
  @InjectMocks
  private TratamientoServicio tratamientoServicio;

  @Mock
  private RepositorioTratamiento repositorioTratamiento;

  private Tratamiento tratamiento;
  private Mascota mascota;
  private Veterinario veterinario;
  private Droga droga;
  private Cliente cliente;

  @BeforeEach
  public void init() {
    cliente = new Cliente();
    cliente.setId(1L);
    cliente.setNombre("c1");
    cliente.setCedula("1");

    mascota = new Mascota();
    mascota.setId(1L);
    mascota.setNombre("m1");
    mascota.setCliente(cliente);

    veterinario = new Veterinario();
    veterinario.setId(1L);
    veterinario.setNombre("v1");
    veterinario.setCedula("1");

    droga = new Droga();
    droga.setId(1L);
    droga.setNombre("d1");

    tratamiento = new Tratamiento();
    tratamiento.setId(1L);
    tratamiento.setNombreTratamiento("t1");
    tratamiento.setMascota(mascota);
    tratamiento.setVeterinarioEncargado(veterinario);
    tratamiento.setDrogaAsignada(droga);
  }

  @Test
  public void tratamientoServicio_addTratamiento_Tratamiento() {
    // Arrange
    when(repositorioTratamiento.save(Mockito.any(Tratamiento.class))).thenReturn(tratamiento);

    // Act
    Tratamiento tratamientoResultado = tratamientoServicio.addTratamiento(tratamiento);

    // Assert
    Assertions.assertThat(tratamientoResultado).isNotNull();
  }

  @Test
  public void tratamientoServicio_findById_Tratamiento() {
    // Arrange
    when(repositorioTratamiento.findById(1L)).thenReturn(Optional.of(tratamiento));

    // Act
    Tratamiento tratamientoResultado = tratamientoServicio.findById(1L);

    // Assert
    Assertions.assertThat(tratamientoResultado).isNotNull();
  }

  @Test
  public void tratamientoServicio_findAll_ListTratamiento() {
    // Arrange
    when(repositorioTratamiento.findAll()).thenReturn(List.of(tratamiento));

    // Act
    List<Tratamiento> tratamientosResultado = tratamientoServicio.findAll();

    // Assert
    Assertions.assertThat(tratamientosResultado).isNotNull();
    Assertions.assertThat(tratamientosResultado.size()).isEqualTo(1L);
  }

  @Test
  public void tratamientoServicio_updateTratamiento_Tratamiento() {
    // Arrange
    when(repositorioTratamiento.findById(1L)).thenReturn(Optional.of(tratamiento));
    when(repositorioTratamiento.save(Mockito.any(Tratamiento.class))).thenReturn(tratamiento);
    Tratamiento tratamientoModificar = tratamientoServicio.findById(1L);
    tratamientoModificar.setNombreTratamiento("modificado");

    // Act
    Tratamiento tratamientoResultado = tratamientoServicio.updateTratamiento(tratamientoModificar);

    // Assert
    Assertions.assertThat(tratamientoResultado).isNotNull();
    Assertions.assertThat(tratamientoResultado.getId()).isEqualTo(1L);
    Assertions.assertThat(tratamientoResultado.getNombreTratamiento()).isEqualTo("modificado");
  }

  @Test
  public void tratamientoServicio_deleteById_NullTratamiento() {
    // Arrange
    when(repositorioTratamiento.findById(1L)).thenReturn(Optional.ofNullable(null));

    // Act
    Tratamiento tratamientoResultado = tratamientoServicio.findById(1L);

    // Assert
    Assertions.assertThat(tratamientoResultado).isNull();
  }
}
