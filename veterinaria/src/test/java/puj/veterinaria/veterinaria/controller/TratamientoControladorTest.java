package puj.veterinaria.veterinaria.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import puj.veterinaria.controladores.ControladorTratamiento;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Droga;
import puj.veterinaria.entidades.Mascota;
import puj.veterinaria.entidades.Tratamiento;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.entidades.DTO.MascotaDTO;
import puj.veterinaria.entidades.DTO.TratamientoDTO;
import puj.veterinaria.servicios.droga.IDrogaServicio;
import puj.veterinaria.servicios.mascota.IMascotaServicio;
import puj.veterinaria.servicios.tratamiento.ITratamientoServicio;
import puj.veterinaria.servicios.veterinario.IVeterinarioServicio;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ControladorTratamiento.class)
@RunWith(SpringRunner.class)
public class TratamientoControladorTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ITratamientoServicio tratamientoServicio;

  @MockBean
  private IMascotaServicio mascotaServicio;

  @MockBean
  private IDrogaServicio drogaServicio;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private IVeterinarioServicio veterinarioServicio;

  @Test
  public void tratamientoControlador_agregarTratamiento_ok() throws Exception {

    Droga drogaPrueba1 = new Droga("drogaPrueba1", 1.0, 1.0, 3, 0);
    drogaPrueba1.setId(1L);
    Veterinario veterinario1 = new Veterinario("12343187634534", "nuevoVeterinario", "1234", "nuevaEspecialidad",
        "foto", true);
    MascotaDTO mascotaDTO1 = new MascotaDTO(-1L, "nuevoMascota", "Perro", 12, 12.0, "x", "foto", false, "12341234");
    Mascota mascota1 = new Mascota(mascotaDTO1);
    mascota1.setId(1L);
    Cliente cliente = new Cliente("12341234", "nuevo", "nuevo@nuevo", "7845628");
    mascota1.setCliente(cliente);

    TratamientoDTO tratamientoDTO1 = new TratamientoDTO(-1L, "TratamientoPrueba1", LocalDate.now(), null, null, null);
    tratamientoDTO1.setDrogaAsignadaID(drogaPrueba1.getId());
    tratamientoDTO1.setVeterinaroCedula(veterinario1.getCedula());
    tratamientoDTO1.setMascotaID(mascota1.getId());

    Tratamiento tratamiento1 = new Tratamiento(tratamientoDTO1);

    when(mascotaServicio.findById(Mockito.anyLong())).thenReturn(mascota1);
    when(drogaServicio.findById(Mockito.anyLong())).thenReturn(drogaPrueba1);
    when(veterinarioServicio.findByCedula(Mockito.anyString())).thenReturn(veterinario1);
    when(mascotaServicio.updateMascota(Mockito.any(Mascota.class))).thenReturn(mascota1);
    when(tratamientoServicio.addTratamiento(Mockito.any(Tratamiento.class))).thenReturn(tratamiento1);
    when(drogaServicio.updateDroga(Mockito.any(Droga.class))).thenReturn(drogaPrueba1);

    ResultActions response = mockMvc.perform(
      post("/tratamiento/add")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(tratamientoDTO1)));
    response.andExpect(status().isCreated());
  }

  @Test
  public void tratamientoControlador_ObtenerTratamientos_ListTratamientos() throws Exception {
    Tratamiento tratamiento1 = new Tratamiento("tratamiento1", LocalDate.now());
    Tratamiento tratamiento2 = new Tratamiento("tratamiento2", LocalDate.now());

    List<Tratamiento> tratamientos = new ArrayList<>();
    tratamientos.add(tratamiento1);
    tratamientos.add(tratamiento2);
    when(tratamientoServicio.findAll()).thenReturn(tratamientos);

    ResultActions response = mockMvc.perform(
      get("/tratamiento/tratamientos")
        .contentType("application/json"));
    response.andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(content().json(objectMapper.writeValueAsString(tratamientos)));

  }

  @Test
  public void tratamientoControlador_eliminarTratamiento_ok() throws Exception {

    doNothing().when(tratamientoServicio).deleteById(Mockito.anyLong());
    ResultActions response = mockMvc.perform(
      delete("/tratamiento/delete/1")
        .contentType("application/json"));
    response.andExpect(status().isOk());
  }

  @Test
  public void tratamientoControlador_obtenerTop3TratamientosMasUnidadesVendidas_ListTratamientos() throws Exception {
    Tratamiento tratamiento1 = new Tratamiento("tratamiento1", LocalDate.now());
    Tratamiento tratamiento2 = new Tratamiento("tratamiento2", LocalDate.now());
    Tratamiento tratamiento3 = new Tratamiento("tratamiento3", LocalDate.now());

    List<Tratamiento> tratamientos = new ArrayList<>();
    tratamientos.add(tratamiento1);
    tratamientos.add(tratamiento2);
    tratamientos.add(tratamiento3);

    when(tratamientoServicio.top3TratamientosMasUnidadesVendidas()).thenReturn(tratamientos);

    ResultActions response = mockMvc.perform(
      get("/tratamiento/get-top3-tratamientos-mas-unidad-vendida")
        .contentType("application/json"));
    response.andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(content().json(objectMapper.writeValueAsString(tratamientos)));
  }

  @Test
  public void tratamientoControlador_obtenerTratamiento_Tratamiento() throws Exception {
    Tratamiento tratamiento = new Tratamiento("tratamiento1", LocalDate.now());
    when(tratamientoServicio.findById(Mockito.anyLong())).thenReturn(tratamiento);

    ResultActions response = mockMvc.perform(
      get("/tratamiento/get-tratamiento/1")
        .contentType("application/json"));

    response.andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(content().json(objectMapper.writeValueAsString(tratamiento)));
  }
}
