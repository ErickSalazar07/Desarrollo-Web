package puj.veterinaria.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import puj.veterinaria.entidades.DTO.MascotaDTO;

// Entidades son un POJO (Plain Old Java Object)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
  
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private String raza;

  @Column(nullable = false)
  private Integer edad;

  @Column(nullable = false)
  private Double peso;

  private String enfermedad;

  @Column(nullable = false)
  private String foto;

  @Column(nullable = false, name = "estado_activo")
  private Boolean estadoActivo;

  @JsonIgnore
  @ManyToOne // usar cedula como foreign key
  @JoinColumn(nullable = false, name = "cliente_cedula", referencedColumnName = "cedula") 
  private Cliente cliente;

  @JsonIgnore
  @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
  private List<Tratamiento> tratamientos;
  
  public Mascota(String nombre, String raza, Integer edad, Double peso, String enfermedad, String foto, 
    Boolean estadoActivo) {
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.peso = peso;
    this.enfermedad = enfermedad;
    this.foto = foto;
    this.estadoActivo = estadoActivo;
  }

// Comportamiento

  public Mascota(MascotaDTO mascotaDTO) {
    this.nombre = mascotaDTO.getNombre();
    this.raza = mascotaDTO.getRaza();
    this.edad = mascotaDTO.getEdad();
    this.peso = mascotaDTO.getPeso();
    this.enfermedad = mascotaDTO.getEnfermedad();
    this.foto = mascotaDTO.getFoto();
    this.estadoActivo = mascotaDTO.getEstadoActivo();
  }
}
