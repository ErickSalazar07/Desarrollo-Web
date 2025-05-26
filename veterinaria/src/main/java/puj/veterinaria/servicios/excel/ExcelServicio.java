package puj.veterinaria.servicios.excel;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioDroga;
import puj.veterinaria.repositorios.RepositorioVeterinario;

@Service
public class ExcelServicio implements IExcelServicio {

  @Autowired
  private RepositorioDroga repositorioDroga;

  @Autowired
  private RepositorioVeterinario repositorioVeterinario;

  @Override
  public void generarExcelDeTablaDroga() {
    Workbook excel = new XSSFWorkbook();
    Sheet hoja = excel.createSheet("Drogas");

    List<Droga> drogas = repositorioDroga.findAll();

    Row fila = hoja.createRow(0);
    fila.createCell(0).setCellValue("id");
    fila.createCell(1).setCellValue("nombre");
    fila.createCell(2).setCellValue("precioCompra");
    fila.createCell(3).setCellValue("precioVenta");
    fila.createCell(4).setCellValue("unidadDisponible");
    fila.createCell(5).setCellValue("unidadVendida");

    for (int i = 0; i < drogas.size(); i++) {
      fila = hoja.createRow(i + 1);
      fila.createCell(0).setCellValue(drogas.get(i).getId());
      fila.createCell(1).setCellValue(drogas.get(i).getNombre());
      fila.createCell(2).setCellValue(drogas.get(i).getPrecioCompra());
      fila.createCell(3).setCellValue(drogas.get(i).getPrecioVenta());
      fila.createCell(4).setCellValue(drogas.get(i).getUnidadDisponible());
      fila.createCell(5).setCellValue(drogas.get(i).getUnidadVendida());
    }

    try(FileOutputStream archivoSalida = new FileOutputStream(Paths.get("Drogas.xlsx").toFile())) {
      excel.write(archivoSalida);
      excel.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void generarExcelDeTablaVeterinario() {
    Workbook excel = new XSSFWorkbook();
    Sheet hoja = excel.createSheet("Veterinarios");

    List<Veterinario> veterinarios = repositorioVeterinario.findAll();

    Row fila = hoja.createRow(0);
    fila.createCell(0).setCellValue("id");
    fila.createCell(1).setCellValue("cedula");
    fila.createCell(2).setCellValue("nombre");
    fila.createCell(3).setCellValue("contraseña");
    fila.createCell(4).setCellValue("especialidad");
    fila.createCell(5).setCellValue("foto(url)");
    fila.createCell(6).setCellValue("activo");

    for (int i = 0; i < veterinarios.size(); i++) {
      fila = hoja.createRow(i + 1);
      fila.createCell(0).setCellValue(veterinarios.get(i).getId());
      fila.createCell(1).setCellValue(veterinarios.get(i).getCedula());
      fila.createCell(2).setCellValue(veterinarios.get(i).getNombre());
      fila.createCell(3).setCellValue(veterinarios.get(i).getContrasena());
      fila.createCell(4).setCellValue(veterinarios.get(i).getEspecialidad());
      fila.createCell(5).setCellValue(veterinarios.get(i).getFoto());
      fila.createCell(6).setCellValue(veterinarios.get(i).getActivo());
    }

    try(FileOutputStream archivoSalida = new FileOutputStream(Paths.get("Veterinarios.xlsx").toFile())) {
      excel.write(archivoSalida);
      excel.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void eliminarExcelPorNombre(String nombreExcel) {
    try {
      boolean eliminado = Files.deleteIfExists(Paths.get(nombreExcel));
      System.out.println((eliminado ? "Archivo eliminado" : "Archivo no encontrado") + ": "
        + Paths.get(nombreExcel).toAbsolutePath());
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
