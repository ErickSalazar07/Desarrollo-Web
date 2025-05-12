package puj.veterinaria.veterinaria.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.servicios.cliente.ClienteServicio;
import puj.veterinaria.servicios.droga.DrogaServicio;
import puj.veterinaria.servicios.mascota.MascotaServicio;
import puj.veterinaria.servicios.tratamiento.TratamientoServicio;
import puj.veterinaria.servicios.veterinario.VeterinarioServicio;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TratamientoServicioTestNaive {
  
  @Autowired
  private TratamientoServicio tratamientoServicio;

  @Autowired
  private VeterinarioServicio veterinarioServicio;

  @Autowired
  private DrogaServicio drogaServicio;

  @Autowired
  private MascotaServicio mascotaServicio;

  @Autowired
  private ClienteServicio clienteServicio;
  
  @BeforeEach
  public void init() {
    Tratamiento tratamiento = new Tratamiento("t1",
      LocalDate.parse("10-02-2025",DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    Veterinario v1 = new Veterinario("1", "n1","c1","e1","f1",true);
    Droga d1 = new Droga("n1", 0.0,0.0, 0, 0);
    Mascota m1 = new Mascota("n1", "r1", 4, 4.5, "e1", "f1", false);
    Cliente c1 = new Cliente("1","n1","c1","1");
    clienteServicio.addCliente(c1);
    m1.setCliente(c1);
    veterinarioServicio.addVeterinario(v1);
    drogaServicio.addDroga(d1);
    mascotaServicio.addMascota(m1);
    tratamiento.setVeterinarioEncargado(v1);
    tratamiento.setDrogaAsignada(d1);
    tratamiento.setMascota(m1);
    tratamientoServicio.addTratamiento(tratamiento);
  }
  
  @Test
  public void tratamientoServicio_addTratamiento_Tratamiento() {
    // Arrange
    Tratamiento tratamiento = new Tratamiento("t2",
      LocalDate.parse("10-02-2025",DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    Veterinario v2 = new Veterinario("2", "n2","c2","e2","f2",true);
    Droga d2 = new Droga("n2", 0.0,0.0, 0, 0);
    Mascota m2 = new Mascota("n2", "r2", 4, 4.5, "e2", "f2", false);
    Cliente c2 = new Cliente("2","n2","c2","2");
    clienteServicio.addCliente(c2);
    m2.setCliente(c2);
    veterinarioServicio.addVeterinario(v2);
    drogaServicio.addDroga(d2);
    mascotaServicio.addMascota(m2);
    tratamiento.setVeterinarioEncargado(v2);
    tratamiento.setDrogaAsignada(d2);
    tratamiento.setMascota(m2);
    tratamientoServicio.addTratamiento(tratamiento);

    // Act
    Tratamiento tratamientoVerificar = tratamientoServicio.addTratamiento(tratamiento);

    // Asssert
    Assertions.assertThat(tratamientoVerificar).isNotNull();
  }
  
  @Test
  public void tratamientoServicio_findById_Tratamiento() {
    // Arrange
    // Act
    Tratamiento tratamientoVerificar = tratamientoServicio.findById(1L);

    // Assert
    Assertions.assertThat(tratamientoVerificar).isNotNull();
  }

  @Test
  public void tratamientoServicio_findAll_ListTratamiento() {
    // Arrange
    // Act
    List<Tratamiento> tratamientosVerificar = tratamientoServicio.findAll();

    // Assert
    Assertions.assertThat(tratamientosVerificar).isNotNull();
    Assertions.assertThat(tratamientosVerificar.size()).isEqualTo(1L);
  }
  
  @Test
  public void tratamientoServicio_updateTratamiento_Tratamiento() {
    // Arrange
    Tratamiento tratamientoModificar = tratamientoServicio.findById(1L);
    // Act
    tratamientoModificar.setNombreTratamiento("modificado");
    Tratamiento tratamientoVerificar = tratamientoServicio.updateTratamiento(tratamientoModificar);
    // Assert
    Assertions.assertThat(tratamientoVerificar).isNotNull();
    Assertions.assertThat(tratamientoVerificar.getNombreTratamiento()).isEqualTo("modificado");
  }

  @Test
  public void tratamientoServicio_deleteById_NullTratamiento() {
    // Arrange
    Tratamiento tratamientoVerificar;
    // Act
    tratamientoServicio.deleteById(1L);
    tratamientoVerificar = tratamientoServicio.findById(1L);
    // Assert
    Assertions.assertThat(tratamientoVerificar).isNull();
  }
  
}
