package puj.veterinaria.entidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Autowired
  RepositorioCliente repositorioCliente;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    cargarClientes();
    cargarMascotas(); //! Se debe cargar clientes antes que mascota, puesto que toda mascota debe tener un cliente
  }

  // Asociamos Mascota con Cliente
  // Lo que se busca es que ninguna Mascota quede sin Cliente
  // Hay Clientes que pueden quedar si mascota, no hay problema
  private void cargarMascotas() {
    String linea;
    Mascota mascota;
    Long random;
    Long CANTIDAD_CLIENTES = repositorioCliente.count();
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/mascotas.txt")))) {
      while((linea = br.readLine()) != null) {
        String datos[] = linea.split(",");
        mascota = new Mascota(datos[0],datos[1],Integer.parseInt(datos[2]),Double.parseDouble(datos[3]),
                  datos[4],datos[5],Boolean.parseBoolean(datos[6]));
        random = ThreadLocalRandom.current().nextLong(1L,CANTIDAD_CLIENTES+1);
        mascota.setCliente(repositorioCliente.findById(random).get());
        repositorioMascota.save(mascota);
      }
    } catch(Exception e) {
      System.err.println("Error al leer el archivo mascotas: " + e.getMessage());
    }
  }

  private void cargarClientes() {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/clientes.txt")))) {
      String linea;
      while((linea = br.readLine()) != null) {
        String datos[] = linea.split(",");
        repositorioCliente.save(new Cliente(datos[0],datos[1],datos[2],datos[3]));
      }
      System.out.println("se cargaron los clientes");
    } catch(Exception e) {
      System.err.println("Error al leer el archivo clientes: " + e.getMessage());
    }
  }
}
