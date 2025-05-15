package puj.veterinaria.entidades.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import puj.veterinaria.entidades.Mascota;

@Data
@NoArgsConstructor
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

  public MascotaDTO(Mascota mascota) {
    this.id = mascota.getId();
    this.nombre = mascota.getNombre();
    this.raza = mascota.getRaza();
    this.edad = mascota.getEdad();
    this.peso = mascota.getPeso();
    this.enfermedad = mascota.getEnfermedad();
    this.foto = mascota.getFoto();
    this.estadoActivo = mascota.getEstadoActivo();
    this.cedulaCliente = mascota.getCliente().getCedula();
  }
}
