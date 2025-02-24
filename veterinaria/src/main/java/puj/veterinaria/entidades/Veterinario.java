package puj.veterinaria.entidades;

import java.util.List;

public class Veterinario {
  private String cedula;
  private String nombre;
  private String contrasena;
  private String especialidad;
  private String foto;
  private List<Tratamiento> tratamientos;


  public Veterinario(String cedula, String nombre, String contrasena, String especialidad, String foto,
      List<Tratamiento> tratamientos) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.contrasena = contrasena;
    this.especialidad = especialidad;
    this.foto = foto;
    this.tratamientos = tratamientos;
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
