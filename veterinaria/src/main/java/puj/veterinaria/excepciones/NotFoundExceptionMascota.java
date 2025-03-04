package puj.veterinaria.excepciones;

import puj.veterinaria.entidades.Mascota;

public class NotFoundExceptionMascota extends RuntimeException {
  private String mensaje;

  public NotFoundExceptionMascota(Integer id) {
    mensaje = "La mascota con ID: " + id;
    mensaje += "\nNO EXISTE";
  }

  public NotFoundExceptionMascota(String mensaje) {
    this.mensaje = mensaje;
  }

  public NotFoundExceptionMascota(Mascota mascota) {

    if(mascota == null || (mascota.getNombre() == null && mascota.getRaza() == null))
      mensaje = "La mascota no fue construida correctamente";
    else {
      mensaje = "La mascota con nombre: " + mascota.getNombre() + "\nY raza: " + mascota.getRaza();
      mensaje += "\nNO FUE ENCONTRADA";
    }
  }

  public String obtenerMensaje() { return mensaje; }
}
