package puj.veterinaria.excepciones;

import puj.veterinaria.entidades.Cliente;

public class NotFoundExceptionCliente extends RuntimeException {
  private String mensaje;
  
  public NotFoundExceptionCliente(Integer id) {
    mensaje = "El cliente con ID: "+id;
    mensaje += "\nNO EXISTE";
  }

  public NotFoundExceptionCliente(String mensaje) {
    this.mensaje = mensaje;
  }

  public NotFoundExceptionCliente(Cliente cliente) {

    if(cliente == null || (cliente.getCorreo() == null && cliente.getCedula() == null))
      mensaje = "El cliente no fue construido correctamente";
    else {
      mensaje = "El cliente con correo: "+ cliente.getCorreo() + "\nY cedula: " + cliente.getCedula();
      mensaje += "\n\n\t\tNO FUE ENCONTRADO";
    }
  }

  public String obtenerMensaje() { return mensaje; }
}
