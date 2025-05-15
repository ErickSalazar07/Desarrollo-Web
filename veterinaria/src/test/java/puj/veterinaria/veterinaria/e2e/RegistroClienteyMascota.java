package puj.veterinaria.veterinaria.e2e;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

public class RegistroClienteyMascota {

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
  public void SystemTest_registroClienteyMascota_MascotaCreada() {
    // Inicializaciones porque el beforeEach no funcionó
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--disable-notifications");
    chromeOptions.addArguments("--disable-extensions");
    // chromeOptions.addArguments("--headless");
    this.driver = new ChromeDriver(chromeOptions);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    // ROL VETERINARIO--------------------------------------------------------------
    // Entrar a login de veterinario
    driver.get("http://localhost:4200/veterinario/login");

    // El veterinario se equivoca en los datos de ingreso
    WebElement inputCedula = driver.findElement(By.id("correo"));
    WebElement inputContrasena = driver.findElement(By.id("cedula"));
    WebElement ingresarButton = driver
        .findElement(By.xpath("//*[@id=\"contenedor_info\"]//div[2]//section//form//button"));
    inputCedula.sendKeys("784");
    inputContrasena.sendKeys("789");
    ingresarButton.click();

    // El veterinario vuelve a poner sus datos y entra a su dash
    inputCedula.clear();
    inputContrasena.clear();
    inputCedula.sendKeys("789");
    inputContrasena.sendKeys("789");
    ingresarButton.click();

    // Veterinario entra a seccipon de clientes y selecciona el botón crear cliente
    WebElement clientesButton = wait
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"clientes\"]")));
    clientesButton.click();
    WebElement ingresarCrearClienteButton = driver.findElement(By.className("btn"));
    ingresarCrearClienteButton.click();

    // Veterinario ingresa mal los datos del cliente (No se registra)
    WebElement inputNombreCliente = driver.findElement(By.id("nombre"));
    WebElement inputCorreoCliente = driver.findElement(By.id("correo"));
    WebElement inputCedulaCliente = driver.findElement(By.id("cedula"));
    WebElement inputCelularCliente = driver.findElement(By.id("celular"));
    WebElement crearClienteButton = driver.findElement(By.className("btn"));

    inputNombreCliente.sendKeys("Pedro (Sin cedula)");
    inputCorreoCliente.sendKeys("pedrinchis@");
    inputCelularCliente.sendKeys("31545678");
    crearClienteButton.click();

    // El veterinario vuelve a poner los datos correctos y crea el cliente
    inputNombreCliente.clear();
    inputCorreoCliente.clear();
    inputCelularCliente.clear();
    inputNombreCliente.sendKeys("Pedro");
    inputCorreoCliente.sendKeys("pedrinchis@");
    inputCedulaCliente.sendKeys("11111");
    inputCelularCliente.sendKeys("11111");
    crearClienteButton.click();

    // Verificar que el cliente se haya creado

    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nombreClientes")));
    List<WebElement> nombresClientes = driver.findElements(By.className("nombreClientes"));

    Assertions.assertThat(nombresClientes.get(nombresClientes.size() - 1).getText())
        .isEqualTo("Pedro");

    // Veterinario entra a seccipon de mascotas y selecciona el botón crear mascota
    WebElement mascotasButton = driver.findElement(By.id("mascotas"));
    mascotasButton.click();
    WebElement ingresarCrearMascotaButton = driver.findElement(By.id("boton-agregarMascota"));
    ingresarCrearMascotaButton.click();
    // Veterinario ingresa los datos de la mascota y la crea
    wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//html//body//app-root//app-crear-mascota//app-formulario-mascota//form//div//button")));
    WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
    WebElement inputEdadMascota = driver.findElement(By.id("edad"));
    WebElement inputPesoMascota = driver.findElement(By.id("peso"));
    WebElement inputImagenMascota = driver.findElement(By.id("imagen"));
    WebElement inputEnfermedadMascota = driver.findElement(By.id("enfermedad"));
    WebElement inputCedulaClienteMascota = driver.findElement(By.id("cedula-cliente"));
    WebElement crearMascotaButton = driver
        .findElement(By.xpath("//html//body//app-root//app-crear-mascota//app-formulario-mascota//form//div//button"));

    inputNombreMascota.sendKeys("Pedrinchis");
    inputEdadMascota.sendKeys("12");
    inputPesoMascota.sendKeys("12");
    inputImagenMascota.sendKeys("x");
    inputEnfermedadMascota.sendKeys("x");
    inputCedulaClienteMascota.sendKeys("11111");
    crearMascotaButton.click();

    // Verificar que la mascota se haya creado
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nombresMascotas")));
    List<WebElement> nombresMascotas = driver.findElements(By.className("nombresMascotas"));

    Assertions.assertThat(nombresMascotas.get(nombresMascotas.size() - 1).getText())
        .isEqualTo("Pedrinchis");

    // ROL CLIENTE--------------------------------------------------------------
    // Entrar a login de cliente
    driver.get("http://localhost:4200/cliente/login");
    WebElement inputLoginCorreoCliente = driver.findElement(By.id("correo"));
    WebElement inputLoginCedulaCliente = driver.findElement(By.id("cedula"));
    WebElement ingresarLoginButton = driver
        .findElement(By.xpath("//*[@id=\"contenedor_info\"]//div[2]//section//form//button"));

    inputLoginCorreoCliente.sendKeys("pedrinchis@");
    inputLoginCedulaCliente.sendKeys("11111");
    ingresarLoginButton.click();

    // El cliente revisa que la mascota se haya creado
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("miMascota-nombre")));
    List<WebElement> nombresMascotasCliente = driver.findElements(By.className("miMascota-nombre"));
    Assertions.assertThat(nombresMascotasCliente.get(nombresMascotasCliente.size() - 1).getText())
        .isEqualTo("Pedrinchis");
    driver.quit();
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }
}
