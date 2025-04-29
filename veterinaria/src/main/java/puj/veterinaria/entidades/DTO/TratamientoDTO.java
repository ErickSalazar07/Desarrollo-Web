package puj.veterinaria.entidades.DTO;

import java.time.LocalDate;

public class TratamientoDTO {
  
  private Long id;
  private String nombreTratamiento;
  private LocalDate fecha;
  private Long drogaAsignadaID;
  private Long mascotaID;
  private String veterinaroCedula;

  public TratamientoDTO() {}

  public TratamientoDTO(Long id, String nombreTratamiento, LocalDate fecha, Long drogaAsignadaID, Long mascotaID,
      String veterinaroCedula) {
    this.id = id;
    this.nombreTratamiento = nombreTratamiento;
    this.fecha = fecha;
    this.drogaAsignadaID = drogaAsignadaID;
    this.mascotaID = mascotaID;
    this.veterinaroCedula = veterinaroCedula;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNombreTratamiento() {
    return nombreTratamiento;
  }
  public void setNombreTratamiento(String nombreTratamiento) {
    this.nombreTratamiento = nombreTratamiento;
  }
  public LocalDate getFecha() {
    return fecha;
  }
  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }
  public Long getDrogaAsignadaID() {
    return drogaAsignadaID;
  }
  public void setDrogaAsignadaID(Long drogaAsignadaID) {
    this.drogaAsignadaID = drogaAsignadaID;
  }
  public Long getMascotaID() {
    return mascotaID;
  }
  public void setMascotaID(Long mascotaID) {
    this.mascotaID = mascotaID;
  }
  public String getVeterinaroCedula() {
    return veterinaroCedula;
  }
  public void setVeterinaroCedula(String veterinaroCedula) {
    this.veterinaroCedula = veterinaroCedula;
  }
}
