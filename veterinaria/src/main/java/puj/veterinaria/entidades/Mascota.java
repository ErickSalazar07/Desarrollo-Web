package puj.veterinaria.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

// Entidades son un POJO (Plain Old Java Object)
@Entity
public class Mascota {
  
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private String raza;

  @Column(nullable = false)
  private Integer edad;

  @Column(nullable = false)
  private Double peso;

  private String enfermedad;

  @Column(nullable = false)
  private String foto;

  @Column(nullable = false)
  private Boolean estadoActivo;

  @ManyToOne // usar cedula como foreign key
  @JoinColumn(nullable = false, name = "cliente_cedula", referencedColumnName = "cedula") 
  private Cliente cliente;

  @OneToMany(mappedBy = "mascota")
  private List<Tratamiento> tratamientos;
  
  public Mascota() { }
  
  public Mascota(String nombre, String raza, Integer edad, Double peso, String enfermedad, String foto, 
  Boolean estadoActivo) {
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.peso = peso;
    this.enfermedad = enfermedad;
    this.foto = foto;
    this.estadoActivo = estadoActivo;
  }

// Comportamiento

// Getters y Setters

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
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
  public Cliente getCliente() { return cliente; }
  public void setCliente(Cliente cliente) { this.cliente = cliente; }
  public List<Tratamiento> getTratamientos() { return tratamientos; }
  public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
  public Boolean getEstadoActivo() { return estadoActivo; }
  public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }
}
