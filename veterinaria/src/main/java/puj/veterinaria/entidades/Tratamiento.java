package puj.veterinaria.entidades;

import java.util.Date;

// Entidades son un POJO (Plain Old Java Object)
public class Tratamiento {
  private Droga drogaAsignada;
  private String id;
  private String nombreTratamiento;
  private Veterinario veterinarioEncargado;
  private Date fecha;
  
  public Tratamiento(Droga drogaAsignada, String id, String nombreTratamiento, Veterinario veterinarioEncargado,
      Date fecha) {
    this.drogaAsignada = drogaAsignada;
    this.id = id;
    this.nombreTratamiento = nombreTratamiento;
    this.veterinarioEncargado = veterinarioEncargado;
    this.fecha = fecha;
  }
  
/*
  Comportamiento
*/
  
/*
  Getters y Setters
*/

  public Droga getDrogaAsignada() { return drogaAsignada; }
  public void setDrogaAsignada(Droga drogaAsignada) { this.drogaAsignada = drogaAsignada; }
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getNombreTratamiento() { return nombreTratamiento; }
  public void setNombreTratamiento(String nombreTratamiento) { this.nombreTratamiento = nombreTratamiento; }
  public Veterinario getVeterinarioEncargado() { return veterinarioEncargado; }
  public void setVeterinarioEncargado(Veterinario veterinarioEncargado) { this.veterinarioEncargado = veterinarioEncargado; }
  public Date getFecha() { return fecha; }
  public void setFecha(Date fecha) { this.fecha = fecha; }
}