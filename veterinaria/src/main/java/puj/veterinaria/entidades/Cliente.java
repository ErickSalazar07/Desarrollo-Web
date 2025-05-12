package puj.veterinaria.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidades son un POJO (Plain Old Java Object)
@Entity
@Data
@NoArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue
  private Long id;
  
  @Column(nullable = false, unique = true) // Cambiar foreign key de id por cedula
  private String cedula;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false, unique = true)
  private String correo;

  @Column(nullable = false)
  private String celular;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Mascota> mascotas = new ArrayList<>();
  
  public Cliente(String cedula, String nombre, String correo, String celular) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.correo = correo;
    this.celular = celular;
  }
}
