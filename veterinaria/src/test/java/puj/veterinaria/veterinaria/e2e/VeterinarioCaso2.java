package puj.veterinaria.veterinaria.e2e;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class VeterinarioCaso2 {
  
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

  @Test
  public void SystemTest_VeterinarioCaso2_agregarDroga() {
    // Se calculan valores iniciales desde las kpis del admin
    Double gananciasIniciales = gananciasTotales();
    Long tratamientosIniciales = tratamientosTotales();
  
    // Suministrar medicamento
  
    // Se calculan valores finales despues de suministrar medicamento tambien desde kpis
    Double gananciasFinales = gananciasTotales();
    Long tratamientosFinales = tratamientosTotales();
  }
  
  private void suministrarMedicamento() {
    driver.get("http://localhost:4200/veterinario/dashboard/789/mascota/mascotas");

    String cssSelector = "section.busqueda input";
    WebElement inpTxtBuscar = driver.findElement(By.cssSelector(cssSelector));
    inpTxtBuscar.sendKeys("Pachini");
  }

  private Double gananciasTotales() {
    // Seccion login
    driver.get("http://localhost:4200/admin/login");
    WebElement inpTxtUsername = driver.findElement(By.id("username"));
    WebElement inpTxtCelular = driver.findElement(By.id("celular"));
    WebElement btnIngresar = driver.findElement(By.cssSelector("form button"));

    inpTxtUsername.sendKeys("admin");
    inpTxtCelular.sendKeys("123");
    btnIngresar.click();

    // Seccion kpis
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
    WebElement inpTxtUsername = driver.findElement(By.id("username"));
    WebElement inpTxtCelular = driver.findElement(By.id("celular"));
    WebElement btnIngresar = driver.findElement(By.cssSelector("form button"));

    inpTxtUsername.sendKeys("admin");
    inpTxtCelular.sendKeys("123");
    btnIngresar.click();

    // Seccion kpis
    WebElement btnKpi = driver.findElement(By.cssSelector("div#kpis"));
    btnKpi.click();

    String selectPTratamientos = "#seccion-tratamientos p";
    WebElement pTratamientos = driver.findElement(By.cssSelector(selectPTratamientos));
    Long tratamientos = Long.parseLong(pTratamientos.getText());
    return tratamientos;
  }
}
