package puj.veterinaria.veterinaria.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import puj.veterinaria.controladores.ControladorTratamiento;

@WebMvcTest(controllers = ControladorTratamiento.class)
@RunWith(SpringRunner.class)
public class TratamientoControladorTest {
    
}
