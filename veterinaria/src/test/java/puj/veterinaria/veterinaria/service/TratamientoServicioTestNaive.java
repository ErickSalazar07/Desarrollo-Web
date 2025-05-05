package puj.veterinaria.veterinaria.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.servicios.TratamientoServicio;

@SpringBootTest
public class TratamientoServicioTestNaive {
  
  @Autowired
  private TratamientoServicio tratamientoServicio;
  
  @Test
  public void tratamientoServicio_addTratamiento_Tratamiento() {
    Tratamiento tratamiento = new Tratamiento("t1",
      LocalDate.parse("10-2-2025",DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    tratamiento.setMascota(new Mascota("m1","r1",4,5.4,"e1","f1",true));
  }
  
}
