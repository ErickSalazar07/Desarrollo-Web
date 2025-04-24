package puj.veterinaria.servicios;

import java.util.List;

import puj.veterinaria.entidades.Tratamiento;

public interface ITratamientoServicio {
  public List<Tratamiento> findAll();
  public Tratamiento findById(Long id);
  public List<Tratamiento> findByMascotaId(Long id);
  public List<Tratamiento> findByVeterinarioEncargado_Cedula(String cedula);
  public void addTratamiento(Tratamiento tratamiento);
  public void updateTratamiento(Long id, Tratamiento tratamiento);
  public void updateTratamiento(Tratamiento tratamiento);
  public void deleteById(Long id);
  public Long cantidadTratamientosUltimoMes();
  public Long cantidadTratamientosTipoMedicamento(String medicamento);
  public List<Tratamiento> top3TratamientosMasUnidadesVendidas();
  public List<Tratamiento> tratamientosVeterinario(String cedula);

}
