package puj.veterinaria.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// Entidades son un POJO (Plain Old Java Object)
@Entity
public class Tratamiento {

  @Id
  @GeneratedValue
  private Long id;
  private String nombreTratamiento;
  private Date fecha;
  
  // private Droga drogaAsignada;

  @ManyToOne
  @JoinColumn(name = "id_mascota", referencedColumnName = "id")
  private Mascota mascota;

  @ManyToOne
  @JoinColumn(name = "cedula_veterinario_encargado", referencedColumnName = "cedula")
  private Veterinario veterinarioEncargado;
  
  public Tratamiento() { }

  public Tratamiento(String nombreTratamiento, Date fecha) {
    this.nombreTratamiento = nombreTratamiento;
    this.fecha = fecha;
  }
  
/*
  Comportamiento
*/
  
/*
  Getters y Setters
*/

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  // public Droga getDrogaAsignada() { return drogaAsignada; }
  // public void setDrogaAsignada(Droga drogaAsignada) { this.drogaAsignada = drogaAsignada; }
  public String getNombreTratamiento() { return nombreTratamiento; }
  public void setNombreTratamiento(String nombreTratamiento) { this.nombreTratamiento = nombreTratamiento; }
  public Veterinario getVeterinarioEncargado() { return veterinarioEncargado; }
  public void setVeterinarioEncargado(Veterinario veterinarioEncargado) { this.veterinarioEncargado = veterinarioEncargado; }
  public Date getFecha() { return fecha; }
  public void setFecha(Date fecha) { this.fecha = fecha; }
  public Mascota getMascota() { return mascota; }
  public void setMascota(Mascota mascota) { this.mascota = mascota; }
}