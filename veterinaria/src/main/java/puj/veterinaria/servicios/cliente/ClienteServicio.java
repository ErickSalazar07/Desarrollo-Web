package puj.veterinaria.servicios.cliente;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.repositorios.RepositorioCliente;

@Service
public class ClienteServicio implements IClienteServicio {

  @Autowired
  RepositorioCliente repositorioCliente;

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public Cliente findById(Long id) {
    return repositorioCliente.findById(id).orElse(null);
  }

  @Override
  public List<Cliente> findAll() {
    return repositorioCliente.findAll();
  }

  @Override
  public Cliente findByCorreoAndCedula(String correo, String cedula) {
    return repositorioCliente.findByCorreoAndCedula(correo, cedula);
  }

  @Override
  public Cliente findByCedula(String cedula) {
    return repositorioCliente.findByCedula(cedula);
  }

  @Override // Logica de negocio para enviar confirmacion por email
  public Cliente addCliente(Cliente cliente) {
    try {

      Cliente clienteAgregado = repositorioCliente.save(cliente);
      
      if(clienteAgregado == null) return null;
      
      MimeMessage confirmacion = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(confirmacion,true);
      
      helper.setFrom("health.animals.eed@gmail.com");
      helper.setTo(cliente.getCorreo());
      helper.setSubject("Health Animals - Confirmación de registro");

      try(var inputStream = Objects.requireNonNull(getClass()
          .getResourceAsStream("/templates/html/confirmacion-email.html"))) {
        
        String htmlTemplate = new String(inputStream.readAllBytes(),StandardCharsets.UTF_8);
        String htmlEmail = htmlTemplate.replace("${cliente.getNombre()}",cliente.getNombre());
        helper.setText(htmlEmail,true);
      } 

      helper.addInline("logo.png", new ClassPathResource("static/images/logo.png"));
      mailSender.send(confirmacion);
  
      return clienteAgregado;
    } catch(Exception e) {
      return null;
    }
  }

  @Transactional
  @Override
  public Cliente updateCliente(Long id, Cliente nuevoCliente) {
    Cliente cliente = repositorioCliente.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

    cliente.setNombre(nuevoCliente.getNombre());
    cliente.setCorreo(nuevoCliente.getCorreo());
    cliente.setCelular(nuevoCliente.getCelular());

    return repositorioCliente.save(cliente);
  }

  @Override
  public Cliente updateCliente(Cliente cliente) {
    return repositorioCliente.save(cliente);
  }

  @Override
  public void deleteById(Long id) {
    repositorioCliente.deleteById(id);
  }

  @Override
  public Long numeroClientes() {
    return repositorioCliente.count();
  }
  @Override
  public Cliente findByCorreo(String correo) {
    return repositorioCliente.findByCorreo(correo);
  }
}
