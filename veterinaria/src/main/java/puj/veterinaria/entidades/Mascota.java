package puj.veterinaria.entidades;

import java.util.List;

// Entidades son un POJO (Plain Old Java Object)
public class Mascota {
  private Integer id;
  private String nombre;
  private String raza;
  private Integer edad;
  private Double peso;
  private String enfermedad;
  private String foto;
  private List<Tratamiento> tratamientos;
  
  public Mascota(Integer id, String nombre, String raza, Integer edad, Double peso,
      String enfermedad, String foto, List<Tratamiento> tratamientos) {
    
    this.id = id;
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.peso = peso;
    this.enfermedad = enfermedad;
    this.foto = foto;
    this.tratamientos = tratamientos;
  }

/*
  Comportamiento
*/

/*
  Getters y Setters
*/
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getRaza() { return raza; }
  public void setRaza(String raza) { this.raza = raza; }
  public Integer getEdad() { return edad; }
  public void setEdad(Integer edad) { this.edad = edad; }
  public Double getPeso() { return peso; }
  public void setPeso(Double peso) { this.peso = peso; }
  public String getEnfermedad() { return enfermedad; }
  public void setEnfermedad(String enfermedad) { this.enfermedad = enfermedad; }
  public String getFoto() { return foto; }
  public void setFoto(String foto) { this.foto = foto; }
  public List<Tratamiento> getTratamientos() { return tratamientos; }
  public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
