package puj.veterinaria.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidades son un POJO (Plain Old Java Object)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {

  @Id
  @GeneratedValue
  private Long id;
  
  @Column(nullable = false, unique = true)
  private String cedula;
  
  @Column(nullable = false)
  private String nombre;
  
  @Column(nullable = false)
  private String contrasena;
  
  @Column(nullable = false)
  private String especialidad;
  
  @Column(nullable = false)
  private String foto;

  @Column(nullable = false)
  private Boolean activo; // Propiedad que indica si el veterinario esta activo o no

  @JsonIgnore
  @OneToMany(mappedBy = "veterinarioEncargado", cascade = CascadeType.ALL)
  private List<Tratamiento> tratamientos;

  @OneToOne(cascade = CascadeType.ALL)
  private UserEntity user;

  public Veterinario(String cedula, String nombre, String contrasena, String especialidad, String foto, Boolean activo) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.contrasena = contrasena;
    this.especialidad = especialidad;
    this.foto = foto;
    this.activo = activo;
  }
}
