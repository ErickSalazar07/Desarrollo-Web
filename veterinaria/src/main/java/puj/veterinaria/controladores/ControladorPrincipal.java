package puj.veterinaria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

  // URL: http://localhost:8090/
  @GetMapping("")
  public String paginaPrincipal() {
    return "html/index";
  }

  // URL: http://localhost:8090/index.html
  @GetMapping("index.html")
  public String paginaIndex() {
    return "html/index";
  }
}
