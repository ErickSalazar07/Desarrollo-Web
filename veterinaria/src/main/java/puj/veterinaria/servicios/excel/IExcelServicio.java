package puj.veterinaria.servicios.excel;

public interface IExcelServicio {
  public void generarExcelDeTablaDroga();
  public void generarExcelDeTablaVeterinario();
  public void eliminarExcelPorNombre(String nombreExcel);
}
