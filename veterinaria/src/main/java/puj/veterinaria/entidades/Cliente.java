package puj.veterinaria.entidades;

import java.util.List;

public class Cliente {
  private String cedula;
  private String nombre;
  private String correo;
  private Integer celular;
  private List<Mascota> mascotas;
  
  public Cliente(String cedula, String nombre, String correo, Integer celular, List<Mascota> mascotas) {
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

  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }
  public Integer getCelular() { return celular; }
  public void setCelular(Integer celular) { this.celular = celular; }
  public List<Mascota> getMascotas() { return mascotas; }
  public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}
