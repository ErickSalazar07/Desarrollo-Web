package puj.veterinaria.veterinaria.controller;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import puj.veterinaria.controladores.ControladorTratamiento;
import puj.veterinaria.servicios.TratamientoServicio;

@WebMvcTest(controllers = ControladorTratamiento.class)
@RunWith(SpringRunner.class)
public class TratamientoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TratamientoServicio tratamientoServicio;

    
    
}
