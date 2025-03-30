package puj.veterinaria.entidades;

import java.util.List;

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
  
  @OneToMany(mappedBy = "veterinarioEncargado", cascade = CascadeType.ALL)
  private List<Tratamiento> tratamientos;

  public Veterinario() { }

  public Veterinario(String cedula, String nombre, String contrasena, String especialidad, String foto) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.contrasena = contrasena;
    this.especialidad = especialidad;
    this.foto = foto;
  }

/*
  Comportamiento
*/

  // TODO: Implementar funcion
  public void asignarTratamiento() {

  }

  // TODO: Implementar funcion
  public Integer numeroAtenciones() {
    return 0;
  }

/*
  Getters y Setters
*/

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
  public List<Tratamiento> getTratamientos() { return tratamientos; }
  public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
