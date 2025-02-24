package puj.veterinaria.entidades;

public class Droga {
  private String nombre;
  private Integer precioCompra;
  private Integer precioVenta;
  private Integer unidadDisponible;
  private Integer unidadVendida;

  public Droga(String nombre, Integer precioCompra, Integer precioVenta,
      Integer unidadDisponible, Integer unidadVendida) {
    this.nombre = nombre;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.unidadDisponible = unidadDisponible;
    this.unidadVendida = unidadVendida;
  }

/*
  Getters y Setters
*/
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
}
