package puj.veterinaria.entidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Autowired
  RepositorioCliente repositorioCliente;

  @Autowired
  RepositorioVeterinario repositorioVeterinario;

  @Autowired
  RepositorioTratamiento repositorioTratamiento;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    cargarVeterinarios();
    cargarClientes();
    cargarMascotas(); //! Se debe cargar clientes antes que mascota, puesto que toda mascota debe tener un cliente
    cargarTratamientos(); //! Se debe cargar primero Mascota y Veterinario, si no da error
  }

  // Asociamos Mascota con Cliente
  // Lo que se busca es que ninguna Mascota quede sin Cliente
  // Hay Clientes que pueden quedar si mascota, no hay problema
  private void cargarMascotas() {
    String linea;
    Mascota mascota;
    Long random;
    Long CANTIDAD_CLIENTES;
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/mascotas.txt")))) {

      CANTIDAD_CLIENTES = repositorioCliente.count();
      Random rng = new Random(42);
      String datos[];
      while((linea = br.readLine()) != null) {
        datos = linea.split(",");
        mascota = new Mascota(datos[0],datos[1],Integer.parseInt(datos[2]),Double.parseDouble(datos[3]),
                  datos[4].equalsIgnoreCase("null") ? null:datos[4],datos[5],
                  Boolean.parseBoolean(datos[6]));
        random = 1L + (rng.nextLong() % CANTIDAD_CLIENTES); 

        if(random < 1L) random += CANTIDAD_CLIENTES;

        mascota.setCliente(repositorioCliente.findById(random).get());
        repositorioMascota.save(mascota);
      }
    } catch(Exception e) {
      System.err.println("Error al leer el archivo mascotas: " + e.getMessage());
    }
  }

  private void cargarTratamientos() {
    String linea;
    String datos[];
    Long CANTIDAD_VETERINARIOS, CANTIDAD_MASCOTAS;
    Long idRandVeterinario, idRandMascota;
    Tratamiento tratamiento;

    
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/tratamientos.txt")))) {

      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      Random randVet = new Random(43);
      Random randMasc = new Random(44);
      CANTIDAD_VETERINARIOS = repositorioVeterinario.count();
      CANTIDAD_MASCOTAS = repositorioMascota.count();

      while((linea = br.readLine()) != null) {
        datos = linea.split(",");
        tratamiento = new Tratamiento(datos[0],formato.parse(datos[1]));

        idRandVeterinario = 1L + (randVet.nextLong()%CANTIDAD_VETERINARIOS);
        if(idRandVeterinario < 1L) idRandVeterinario += CANTIDAD_VETERINARIOS;

        idRandMascota = 1L + (randMasc.nextLong()%CANTIDAD_MASCOTAS);
        if(idRandMascota < 1L) idRandMascota += CANTIDAD_MASCOTAS;

        tratamiento.setVeterinarioEncargado(repositorioVeterinario.findById(idRandVeterinario).get());
        tratamiento.setMascota(repositorioMascota.findById(idRandMascota).get());

        repositorioTratamiento.save(tratamiento);
      }
    } catch(ParseException e) {
      System.err.println("Error al interpretar el formato de la fecha: " + e.getMessage());
    } catch(Exception e) {
      System.err.println("Ocurrio un error: " + e.getMessage());
    }
  }

  private void cargarClientes() {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/clientes.txt")))) {
      String linea;
      String datos[];
      while((linea = br.readLine()) != null) {
        datos = linea.split(",");
        repositorioCliente.save(new Cliente(datos[0],datos[1],datos[2],datos[3]));
      }
      System.out.println("se cargaron los clientes");
    } catch(Exception e) {
      System.err.println("Error al leer el archivo clientes: " + e.getMessage());
    }
  }

  private void cargarVeterinarios() {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/veterinarios.txt")))) {
      String linea;
      String datos[];
      while((linea = br.readLine()) != null) {
        datos = linea.split(",");
        repositorioVeterinario.save(new Veterinario(datos[2],datos[0],datos[1],datos[3],datos[4]));
      }
      System.out.println("se cargaron los veterinarios");
    } catch(Exception e) {
      System.err.println("Error al leer el archivo veterinarios: "+ e.getMessage());
    }
  }
}
