package puj.veterinaria.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Entidades son un POJO (Plain Old Java Object)
@Entity
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

  public Veterinario() { }

  public Veterinario(String cedula, String nombre, String contrasena, String especialidad, String foto, Boolean activo) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.contrasena = contrasena;
    this.especialidad = especialidad;
    this.foto = foto;
    this.activo = activo;
  }

// Getters y Setters

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getContrasena() { return contrasena; }
  public void setContrasena(String contrasena) { this.contrasena = contrasena; }
  public String getEspecialidad() { return especialidad; }
  public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
  public String getFoto() { return foto; }
  public void setFoto(String foto) { this.foto = foto; }
  public Boolean getActivo() { return activo; }
  public void setActivo(Boolean activo) { this.activo = activo; }
  public List<Tratamiento> getTratamientos() { return tratamientos; }
  public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
