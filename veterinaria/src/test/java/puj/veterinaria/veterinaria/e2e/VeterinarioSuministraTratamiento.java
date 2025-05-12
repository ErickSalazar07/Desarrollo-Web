package puj.veterinaria.veterinaria.e2e;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class VeterinarioSuministraTratamiento {
  
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  public void init() {

    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.addArguments("--disable-notifications");
    chromeOptions.addArguments("--disable-extensions");
    // chromeOptions.addArguments("--headless");

    this.driver = new ChromeDriver(chromeOptions);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }

  @Test
  public void SystemTest_VeterinarioSuministraTratamiento_valoresKpis() {

    // Se calculan valores iniciales desde las kpis del admin
    Double gananciasIniciales = gananciasTotales();
    Long tratamientosIniciales = tratamientosTotales();
  
    // Suministrar medicamento
    suministrarMedicamento();

    // Se calculan valores finales despues de suministrar medicamento tambien desde kpis
    Double gananciasFinales = gananciasTotales();
    Long tratamientosFinales = tratamientosTotales();

    // Assert
    Assertions.assertThat(tratamientosIniciales+1).isEqualTo(tratamientosFinales);
    Assertions.assertThat(gananciasIniciales).isLessThan(gananciasFinales);
  }
  
  private void suministrarMedicamento() {
    driver.get("http://localhost:4200/veterinario/dashboard/789/mascota/mascotas");
    
    Map<String,String> mascota = Map.of("nombre","Pachini","raza","Gato");

    String selectInptBusqueda = "section.busqueda input";
    WebElement inpTxtBuscar = driver.findElement(By.cssSelector(selectInptBusqueda));
    inpTxtBuscar.sendKeys(mascota.get("nombre"));

    String selectTdNombre = "main tbody td.nombresMascotas";
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectTdNombre)));
    WebElement tdNombre = driver.findElement(By.cssSelector(selectTdNombre));

    String selectTdRaza = "main tbody td.razaMascotas";
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectTdRaza)));
    WebElement tdRaza = driver.findElement(By.cssSelector(selectTdRaza));

    // Verifica que si busco a la mascota correcta y que se encuentra en las mascotas
    Assertions.assertThat(tdNombre.getText()).isEqualTo(mascota.get("nombre"));
    Assertions.assertThat(tdRaza.getText()).isEqualTo(mascota.get("raza"));

    // Seccion buscar y redirigirse a tratamiento
    String selectDivTratamientos = "#tratamientos";
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectDivTratamientos)));
    WebElement divTratamientos = driver.findElement(By.cssSelector(selectDivTratamientos));
    divTratamientos.click();

    String selectAnchorAgregarTratamiento = "a#boton-agregarMascota";
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectAnchorAgregarTratamiento)));
    WebElement anchorAgregarTratamiento = driver.findElement(By.cssSelector(selectAnchorAgregarTratamiento));
    anchorAgregarTratamiento.click();

    // Seccion Asignar Tratamiento
    String selectDivForm = "div.formulario";
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectDivForm)));

    WebElement inpTxtNomTratamiento = driver.findElement(By.cssSelector("#nombreTratamiento"));
    WebElement inpDateFecha = driver.findElement(By.cssSelector("#fecha"));
    List<WebElement> optionsMascota = driver.findElements(By.cssSelector("select#mascota option"));
    List<WebElement> optionsDroga = driver.findElements(By.cssSelector("select#droga option"));
    WebElement btnAsignarTratamiento = driver.findElement(By.cssSelector(selectDivForm + " button"));

    inpTxtNomTratamiento.sendKeys("Tratamiento test e2e");
    inpDateFecha.sendKeys(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

    for(WebElement option: optionsMascota) 
      if(option.getText().equalsIgnoreCase(mascota.get("nombre"))) {
        option.click();
        break;
      }
    
    optionsDroga.get(1).click();
    btnAsignarTratamiento.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contenido")));
  }

  private Double gananciasTotales() {
    // Seccion login
    driver.get("http://localhost:4200/admin/login");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
    WebElement inpTxtUsername = driver.findElement(By.id("username"));
    WebElement inpTxtCelular = driver.findElement(By.id("celular"));
    WebElement btnIngresar = driver.findElement(By.cssSelector("form button"));

    inpTxtUsername.sendKeys("admin");
    inpTxtCelular.sendKeys("123");
    btnIngresar.click();

    // Seccion kpis
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#kpis")));
    WebElement btnKpi = driver.findElement(By.cssSelector("div#kpis"));
    btnKpi.click();

    String selectPGanancia = "#seccion-ganancias p";
    WebElement pGanancias = driver.findElement(By.cssSelector(selectPGanancia));
    Double ganancias = Double.parseDouble(pGanancias.getText().replace(",","").substring(2));
    return ganancias;
  }
  
  private Long tratamientosTotales() {
    // Seccion login
    driver.get("http://localhost:4200/admin/login");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
    WebElement inpTxtUsername = driver.findElement(By.id("username"));
    WebElement inpTxtCelular = driver.findElement(By.id("celular"));
    WebElement btnIngresar = driver.findElement(By.cssSelector("form button"));

    inpTxtUsername.sendKeys("admin");
    inpTxtCelular.sendKeys("123");
    btnIngresar.click();

    // Seccion kpis
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#kpis")));
    WebElement btnKpi = driver.findElement(By.cssSelector("div#kpis"));
    btnKpi.click();

    String selectPTratamientos = "#seccion-tratamientos p";
    WebElement pTratamientos = driver.findElement(By.cssSelector(selectPTratamientos));
    Long tratamientos = Long.parseLong(pTratamientos.getText());
    return tratamientos;
  }
}
