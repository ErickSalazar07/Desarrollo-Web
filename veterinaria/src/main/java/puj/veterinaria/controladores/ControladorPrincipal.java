package puj.veterinaria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

  @GetMapping("")
  public String paginaPrincipal() {
    return "html/index";
  }

  @GetMapping("index.html")
  public String paginaIndex() {
    return "html/index";
  }

  @GetMapping()
  public 
  List<String> animales = new ArrayList<>();

}
