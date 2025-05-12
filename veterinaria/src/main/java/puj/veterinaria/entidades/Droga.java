package puj.veterinaria.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidades son un POJO (Plain Old Java Object)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Droga {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false, name = "precio_compra")
  private Double precioCompra;

  @Column(nullable = false, name = "precio_venta")
  private Double precioVenta;

  @Column(nullable = false, name = "unidad_disponible")
  private Integer unidadDisponible;

  @Column(nullable = false, name = "unidad_vendida")
  private Integer unidadVendida;

  @JsonIgnore
  @OneToMany(mappedBy = "drogaAsignada", cascade = CascadeType.ALL)
  private List<Tratamiento> tratamientos;

  public Droga(String nombre, Double precioCompra, Double precioVenta,
      Integer unidadDisponible, Integer unidadVendida) {
    this.nombre = nombre;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.unidadDisponible = unidadDisponible;
    this.unidadVendida = unidadVendida;
  }
}
