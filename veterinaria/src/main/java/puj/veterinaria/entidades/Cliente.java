package puj.veterinaria.entidades;

import java.util.List;

// Entidades son un POJO (Plain Old Java Object)
public class Cliente {
  private Integer id;
  private String cedula;
  private String nombre;
  private String correo;
  private Long celular;
  private List<Mascota> mascotas;
  
  public Cliente(Integer id, String cedula, String nombre, String correo,
  Long celular, List<Mascota> mascotas) {
    this.id = id;
    this.cedula = cedula;
    this.nombre = nombre;
    this.correo = correo;
    this.celular = celular;
    this.mascotas = mascotas;
  }

/*
  Comportamiento
*/

/*
  Getters y Setters
*/

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }
  public Long getCelular() { return celular; }
  public void setCelular(Long celular) { this.celular = celular; }
  public List<Mascota> getMascotas() { return mascotas; }
  public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}
