package puj.veterinaria.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Entidades son un POJO (Plain Old Java Object)
@Entity
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

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Mascota> mascotas = new ArrayList<>();
  
  public Cliente() { }
  
  public Cliente(String cedula, String nombre, String correo, String celular) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.correo = correo;
    this.celular = celular;
  }

// Comportamiento

// Getters y Setters

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }
  public String getCelular() { return celular; }
  public void setCelular(String celular) { this.celular = celular; }
  public List<Mascota> getMascotas() { return mascotas; }
  public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}
