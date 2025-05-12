package puj.veterinaria.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import puj.veterinaria.entidades.DTO.TratamientoDTO;

// Entidades son un POJO (Plain Old Java Object)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tratamiento {

  @Id
  @GeneratedValue
  private Long id;
  
  @Column(nullable = false, name = "nombre_tratamiento")
  private String nombreTratamiento;
  
  @Column(nullable = false)
  private LocalDate fecha;
  
  @ManyToOne
  @JoinColumn(nullable = false, name = "id_droga", referencedColumnName = "id")
  private Droga drogaAsignada;

  @ManyToOne
  @JoinColumn(nullable = false, name = "id_mascota", referencedColumnName = "id")
  private Mascota mascota;

  @ManyToOne // Se usa la cedula del Veterinario como (foreign-key)
  @JoinColumn(nullable = false, name = "cedula_veterinario", referencedColumnName = "cedula")
  private Veterinario veterinarioEncargado;
  
  public Tratamiento(String nombreTratamiento, LocalDate fecha) {
    this.nombreTratamiento = nombreTratamiento;
    this.fecha = fecha;
  }

  public Tratamiento(TratamientoDTO tratamientoDTO) {
    this.id = null;
    this.nombreTratamiento = tratamientoDTO.getNombreTratamiento();
    this.fecha = tratamientoDTO.getFecha();
  }
}