package puj.veterinaria.entidades;

import java.util.ArrayList;
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
public class Droga {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false, name = "precio_compra")
  private Integer precioCompra;

  @Column(nullable = false, name = "precio_venta")
  private Integer precioVenta;

  @Column(nullable = false, name = "unidad_disponible")
  private Integer unidadDisponible;

  @Column(nullable = false, name = "unidad_vendida")
  private Integer unidadVendida;

  //@JsonIgnore
  //@OneToMany(mappedBy = "drogaAsignada", cascade = CascadeType.ALL)
  //private List<Tratamiento> tratamientos = new ArrayList<>();

  public Droga(String nombre, Integer precioCompra, Integer precioVenta,
      Integer unidadDisponible, Integer unidadVendida) {
    this.nombre = nombre;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.unidadDisponible = unidadDisponible;
    this.unidadVendida = unidadVendida;
  }



// Getters y Setters

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public Integer getPrecioCompra() { return precioCompra; }
  public void setPrecioCompra(Integer precioCompra) { this.precioCompra = precioCompra; }
  public Integer getPrecioVenta() { return precioVenta; }
  public void setPrecioVenta(Integer precioVenta) { this.precioVenta = precioVenta; }
  public Integer getUnidadDisponible() { return unidadDisponible; }
  public void setUnidadDisponible(Integer unidadDisponible) { this.unidadDisponible = unidadDisponible; }
  public Integer getUnidadVendida() { return unidadVendida; }
  public void setUnidadVendida(Integer unidadVendida) { this.unidadVendida = unidadVendida; }
  //public List<Tratamiento> getTratamientos() { return tratamientos; }
  //public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
