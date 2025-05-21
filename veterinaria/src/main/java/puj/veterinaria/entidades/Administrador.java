package puj.veterinaria.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Administrador {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false, unique = true)
  private String correo;

  @Column(nullable = false)
  private String celular;

  @OneToOne(cascade = CascadeType.ALL)
  private UserEntity user;
}
