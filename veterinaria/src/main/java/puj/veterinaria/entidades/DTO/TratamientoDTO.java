package puj.veterinaria.entidades.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TratamientoDTO {
  
  private Long id;
  private String nombreTratamiento;
  private LocalDate fecha;
  private Long drogaAsignadaID;
  private Long mascotaID;
  private String veterinaroCedula;

  public TratamientoDTO(Long id, String nombreTratamiento, LocalDate fecha, Long drogaAsignadaID, Long mascotaID,
      String veterinaroCedula) {
    this.id = id;
    this.nombreTratamiento = nombreTratamiento;
    this.fecha = fecha;
    this.drogaAsignadaID = drogaAsignadaID;
    this.mascotaID = mascotaID;
    this.veterinaroCedula = veterinaroCedula;
  }
}
