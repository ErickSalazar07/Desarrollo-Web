package puj.veterinaria.entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioDroga;
import puj.veterinaria.repositorios.RepositorioMascota;
import puj.veterinaria.repositorios.RepositorioTratamiento;
import puj.veterinaria.repositorios.RepositorioVeterinario;
import puj.veterinaria.repositorios.RepositorioAdmin;


@Controller
@Transactional
@Profile("default")
public class DatabaseInit implements ApplicationRunner {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Autowired
  RepositorioCliente repositorioCliente;

  @Autowired
  RepositorioVeterinario repositorioVeterinario;

  @Autowired
  RepositorioAdmin repositorioAdmin;

  @Autowired
  RepositorioTratamiento repositorioTratamiento;

  @Autowired
  RepositorioDroga repositorioDroga;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    cargarVeterinarios();
    cargarClientes();
    cargarDrogas();
    cargarMascotas(); //! Se debe cargar clientes antes que mascota, puesto que toda mascota debe tener un cliente
    cargarTratamientos(); //! Se debe cargar primero Tratamiento Mascota y Veterinario, si no da error
    crearAdminPorDefecto();
  }

  // Asociamos Mascota con Cliente.
  // Lo que se busca es que ninguna Mascota quede sin Cliente.
  // Hay Clientes que pueden quedar si mascota, no hay problema.
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
      System.out.println("\n\n\n\033[36mSE CARGARON LAS MASCOTAS.\033[0m\n\n\n");
    } catch(Exception e) {
      System.err.println("Error al leer el archivo mascotas: " + e.getMessage());
    }
  }

  private void cargarTratamientos() {
    String linea;
    String datos[];
    Long CANTIDAD_VETERINARIOS, CANTIDAD_MASCOTAS, CANTIDAD_DROGAS;
    Long idRandVeterinario, idRandMascota, idRandDroga;
    Tratamiento tratamiento;

    
    try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
    getResourceAsStream("init-data/tratamientos.txt")))) {

      DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      Random randVet = new Random(43);
      Random randMasc = new Random(44);
      Random randDrog = new Random(45);

      Droga droga;
      Mascota mascota;


      CANTIDAD_VETERINARIOS = repositorioVeterinario.count();
      CANTIDAD_MASCOTAS = repositorioMascota.count();
      CANTIDAD_DROGAS = repositorioDroga.count();

      while((linea = br.readLine()) != null) {
        datos = linea.split(",");
        tratamiento = new Tratamiento(datos[0],LocalDate.parse(datos[1],formato));

        idRandVeterinario = 1L + (randVet.nextLong()%CANTIDAD_VETERINARIOS);
        if(idRandVeterinario < 1L) idRandVeterinario += CANTIDAD_VETERINARIOS;

        idRandMascota = 1L + (randMasc.nextLong()%CANTIDAD_MASCOTAS);
        if(idRandMascota < 1L) idRandMascota += CANTIDAD_MASCOTAS;

        do {
          idRandDroga = 1L + (randDrog.nextLong()%CANTIDAD_DROGAS);
          if(idRandDroga < 1L) idRandDroga += CANTIDAD_DROGAS;
        } while(repositorioDroga.findById(idRandDroga).get().getUnidadDisponible() <= 0);

        mascota = repositorioMascota.findById(idRandMascota).get();
        mascota.setEstadoActivo(true);
        repositorioMascota.save(mascota);
        
        droga = repositorioDroga.findById(idRandDroga).get();
        droga.setUnidadDisponible(droga.getUnidadDisponible()-1);
        droga.setUnidadVendida(droga.getUnidadVendida()+1);
        repositorioDroga.save(droga);
        
        tratamiento.setVeterinarioEncargado(repositorioVeterinario.findById(idRandVeterinario).get());
        tratamiento.setMascota(mascota);
        tratamiento.setDrogaAsignada(droga);
        repositorioTratamiento.save(tratamiento);
      }
    } catch(DateTimeException e) {
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
      System.out.println("\n\n\n\033[36mSE CARGARON LOS CLIENTES.\033[0m\n\n\n");
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
        repositorioVeterinario.save(new Veterinario(datos[2],datos[0],datos[1],datos[3],datos[4],datos[5].equalsIgnoreCase("si")));
      }
      System.out.println("\n\n\n\033[36mSE CARGARON LOS VETERINARIOS.\033[0m\n\n\n");
    } catch(Exception e) {
      System.err.println("Error al leer el archivo veterinarios: "+ e.getMessage());
    }
  }

  private void cargarDrogas() {
    try(InputStream archivoExcel = getClass().getClassLoader().
    getResourceAsStream("init-data/MEDICAMENTOS_VETERINARIA.xlsx")) {

      String nombre;
      Double precioVenta, precioCompra;
      Integer unidadVendida, unidadDisponible;

      Workbook libro = new XSSFWorkbook(archivoExcel);
      Sheet hoja = libro.getSheetAt(0);
      String precioVentaStr;
      String precioCompraStr;

      for(Row fila: hoja) {
        if(fila.getRowNum() == 0) continue;

        nombre = obtenerTextoDesdeCelda(fila.getCell(0));
        precioVentaStr = obtenerTextoDesdeCelda(fila.getCell(1));
        precioCompraStr = obtenerTextoDesdeCelda(fila.getCell(2));
        unidadDisponible = (int)(fila.getCell(3).getNumericCellValue());
        unidadVendida = (int)fila.getCell(4).getNumericCellValue();

        precioCompra = parsearPrecio(precioCompraStr);
        precioVenta = parsearPrecio(precioVentaStr);

        repositorioDroga.
        save(new Droga(nombre,precioCompra,precioVenta,unidadDisponible,unidadVendida));
      }
      libro.close();
      System.out.println("\n\n\n\033[36mSE CARGARON LAS DROGAS.\033[0m\n\n\n");
    } catch(IOException e) {
      System.err.println("Error: Leyendo el archivo Excel. " + e.getMessage());
    } catch(NumberFormatException e) {
      System.err.println("Error: Interprentado alguna celda del archivo excel. " + e.getMessage());
    } catch(NullPointerException e) {
      System.err.println("Error: Se derefencio un puntero nulo, verificar. " + e.getMessage());
      e.printStackTrace();
    } catch(Exception e) {
      System.err.println("Error: Ocurrio un error. " + e.getMessage());
    }
  }

  private double parsearPrecio(String texto) {
    return Double.parseDouble(
      texto.replace("$", "")
           .replace(",", "")
           .trim()
    );
  }

  private String obtenerTextoDesdeCelda(Cell celda) {

    if (celda == null) return "";

    switch (celda.getCellType()) {

      case STRING:
        return celda.getStringCellValue();

      case NUMERIC:
        return String.valueOf(celda.getNumericCellValue());

      case FORMULA:
        switch(celda.getCachedFormulaResultType()) {

          case STRING:
            return celda.getStringCellValue();

          case NUMERIC:
            return String.valueOf(celda.getNumericCellValue());

          case BOOLEAN:
            return String.valueOf(celda.getBooleanCellValue());

      // Se devuelve String vacio, puesto que no es de interes procesar estos tipos de celdas
          case FORMULA:
          case ERROR:
          case BLANK:
          case _NONE:
            return "";
        }

      case BOOLEAN:
        return String.valueOf(celda.getBooleanCellValue());
      
      // Se devuelve String vacio, puesto que no es de interes procesar estos tipos de celdas
      case BLANK:
      case _NONE:
      case ERROR:
        return "";
    }

    return "";
  }

  private void crearAdminPorDefecto() {
    try {
        if (repositorioAdmin.count() == 0) { // Solo lo crea si no hay admins aún
            Administrador admin = new Administrador();
            admin.setUsername("admin");
            admin.setNombre("Administrador General");
            admin.setCorreo("admin@veterinaria");
            admin.setCelular("123");

            repositorioAdmin.save(admin);
            System.out.println("\n\n\n\033[32mADMINISTRADOR POR DEFECTO CREADO.\033[0m\n\n\n");
        } else {
            System.out.println("\n\n\n\033[33mYA EXISTEN ADMINISTRADORES EN LA BD. NO SE CREÓ NINGUNO NUEVO.\033[0m\n\n\n");
        }
    } catch (Exception e) {
        System.err.println("Error al crear el administrador por defecto: " + e.getMessage());
    }
}

}
