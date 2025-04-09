package puj.veterinaria.entidades.DTO;

public class MascotaDTO {
  private Long id;
  private String nombre;
  private String raza;
  private Integer edad;
  private Double peso;
  private String enfermedad;
  private String foto;
  private Boolean estadoActivo;
  private String cedulaCliente;

  public MascotaDTO() { }

  public MascotaDTO(Long id, String nombre, String raza, Integer edad, Double peso,
  String enfermedad, String foto, Boolean estadoActivo, String cedulaCliente) {
    this.id = id;
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.peso = peso;
    this.enfermedad = enfermedad;
    this.foto = foto;
    this.estadoActivo = estadoActivo;
    this.cedulaCliente = cedulaCliente;
  }

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
  public Boolean getEstadoActivo() { return estadoActivo; }
  public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }
  public String getCedulaCliente() { return cedulaCliente; }
  public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}
