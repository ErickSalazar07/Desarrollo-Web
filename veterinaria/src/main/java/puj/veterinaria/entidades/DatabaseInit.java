package puj.veterinaria.entidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
import puj.veterinaria.repositorios.RepositorioCliente;
import puj.veterinaria.repositorios.RepositorioMascota;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {

  @Autowired
  RepositorioMascota repositorioMascota;

  @Autowired
  RepositorioCliente repositorioCliente;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    repositorioMascota.save(new Mascota( "Pachini","Gato",5,4.5,"VIF",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-pachini.jpg?raw=true",false));
    repositorioMascota.save(new Mascota("Zeus","Perro",9,11.7,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-zeus.jpg?raw=true",true));
    repositorioMascota.save(new Mascota("Figaro","Perro",9,19.7,"Cataratas",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-figaro.jpg?raw=true",true));
    repositorioMascota.save(new Mascota("Lola","Gato",3,6.0,null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-lola.jpg?raw=true",true));
    repositorioMascota.save(new Mascota("Atenea","Perro",3,4.5,"Virus Rabia",
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-atenea.jpg?raw=true",true));

    //50 Perros:
    repositorioMascota.save(new Mascota("Max", "Perro", 2, 5.3, null,
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p1.png?raw=true"));
    repositorioMascota.save(new Mascota("Rocky", "Perro", 4, 7.2, "Parvovirus",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p2.png?raw=true"));
    repositorioMascota.save(new Mascota("Bruno", "Perro", 1, 4.1, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p3.png?raw=true"));
    repositorioMascota.save(new Mascota("Toby", "Perro", 5, 6.8, "Moquillo",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p4.png?raw=true"));
    repositorioMascota.save(new Mascota("Simba", "Perro", 3, 5.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p5.png?raw=true"));
    repositorioMascota.save(new Mascota("Luna", "Perro", 2, 4.5, "Gastroenteritis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p6.png?raw=true"));
    repositorioMascota.save(new Mascota("Coco", "Perro", 6, 8.3, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p7.png?raw=true"));
    repositorioMascota.save(new Mascota("Milo", "Perro", 4, 6.2, "Otitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p8.png?raw=true"));
    repositorioMascota.save(new Mascota("Duke", "Perro", 3, 7.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p9.png?raw=true"));
    repositorioMascota.save(new Mascota("Rex", "Perro", 1, 5.5, "Sarna",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p10.png?raw=true"));
    repositorioMascota.save(new Mascota("Thor", "Perro", 5, 9.1, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p11.png?raw=true"));
    repositorioMascota.save(new Mascota("Bobby", "Perro", 2, 6.0, "Leptospirosis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p12.png?raw=true"));
    repositorioMascota.save(new Mascota("Zeus", "Perro", 4, 7.4, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p13.png?raw=true"));
    repositorioMascota.save(new Mascota("Firulais", "Perro", 3, 5.9, "Gingivitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p14.png?raw=true"));
    repositorioMascota.save(new Mascota("Sultan", "Perro", 6, 8.5, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p15.png?raw=true"));
    repositorioMascota.save(new Mascota("Balto", "Perro", 1, 4.7, "Dermatitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p16.png?raw=true"));
    repositorioMascota.save(new Mascota("Chester", "Perro", 3, 6.5, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p17.png?raw=true"));
    repositorioMascota.save(new Mascota("Jack", "Perro", 2, 5.2, "Infección Urinaria",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p18.png?raw=true"));
    repositorioMascota.save(new Mascota("Baxter", "Perro", 4, 7.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p19.png?raw=true"));
    repositorioMascota.save(new Mascota("Spike", "Perro", 5, 8.0, "Otitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p20.png?raw=true"));
    repositorioMascota.save(new Mascota("Hugo", "Perro", 3, 6.1, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p21.png?raw=true"));
    repositorioMascota.save(new Mascota("Benji", "Perro", 2, 4.9, "Parásitos Intestinales",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p22.png?raw=true"));
    repositorioMascota.save(new Mascota("Tommy", "Perro", 6, 9.2, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p23.png?raw=true"));
    repositorioMascota.save(new Mascota("Lucas", "Perro", 1, 4.3, "Alergias",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p24.png?raw=true"));
    repositorioMascota.save(new Mascota("Rocco", "Perro", 4, 7.3, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p25.png?raw=true"));
        repositorioMascota.save(new Mascota("Leo", "Perro", 3, 5.6, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p1.png?raw=true"));
    repositorioMascota.save(new Mascota("Oscar", "Perro", 2, 6.4, "Anemia",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p2.png?raw=true"));
    repositorioMascota.save(new Mascota("Nico", "Perro", 5, 7.8, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p3.png?raw=true"));
    repositorioMascota.save(new Mascota("Felix", "Perro", 4, 6.1, "Conjuntivitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p4.png?raw=true"));
    repositorioMascota.save(new Mascota("Teddy", "Perro", 1, 5.3, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p5.png?raw=true"));
    repositorioMascota.save(new Mascota("Lenny", "Perro", 6, 8.2, "Gripe Canina",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p6.png?raw=true"));
    repositorioMascota.save(new Mascota("Diego", "Perro", 3, 5.7, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p7.png?raw=true"));
    repositorioMascota.save(new Mascota("Rufus", "Perro", 2, 6.9, "Otitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p8.png?raw=true"));
    repositorioMascota.save(new Mascota("Charlie", "Perro", 4, 7.1, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p9.png?raw=true"));
    repositorioMascota.save(new Mascota("Harry", "Perro", 5, 8.5, "Diarrea",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p10.png?raw=true"));
    repositorioMascota.save(new Mascota("Kevin", "Perro", 3, 5.4, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p11.png?raw=true"));
    repositorioMascota.save(new Mascota("Alan", "Perro", 1, 4.8, "Tos de las perreras",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p12.png?raw=true"));
    repositorioMascota.save(new Mascota("Julius", "Perro", 2, 6.2, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p13.png?raw=true"));
    repositorioMascota.save(new Mascota("Samuel", "Perro", 4, 7.6, "Hipotiroidismo",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p14.png?raw=true"));
    repositorioMascota.save(new Mascota("Daniel", "Perro", 6, 9.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p15.png?raw=true"));
    repositorioMascota.save(new Mascota("Brayan", "Perro", 5, 7.2, "Gingivitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p16.png?raw=true"));
    repositorioMascota.save(new Mascota("Esteban", "Perro", 1, 5.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p17.png?raw=true"));
    repositorioMascota.save(new Mascota("Marcos", "Perro", 3, 6.8, "Parásitos",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p18.png?raw=true"));
    repositorioMascota.save(new Mascota("Martin", "Perro", 2, 6.0, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p19.png?raw=true"));
    repositorioMascota.save(new Mascota("Hector", "Perro", 4, 7.3, "Epilepsia",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p20.png?raw=true"));
    repositorioMascota.save(new Mascota("Pablo", "Perro", 6, 9.5, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p21.png?raw=true"));
    repositorioMascota.save(new Mascota("Ivan", "Perro", 5, 8.1, "Alergias",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p22.png?raw=true"));
    repositorioMascota.save(new Mascota("Rodrigo", "Perro", 3, 5.9, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p23.png?raw=true"));
    repositorioMascota.save(new Mascota("Sergio", "Perro", 2, 6.4, "Otitis",
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p24.png?raw=true"));
    repositorioMascota.save(new Mascota("Francisco", "Perro", 1, 5.1, null,
        "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/p25.png?raw=true"));



    //50 gatos
    repositorioMascota.save(new Mascota("Felix", "Gato", 2, 3.8, "Resfriado", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/1g.png?raw=true"));
repositorioMascota.save(new Mascota("Milo", "Gato", 4, 4.2, "Parásitos", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/2g.png?raw=true"));
repositorioMascota.save(new Mascota("Simba", "Gato", 5, 4.7, "Gripe Felina", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/3g.png?raw=true"));
repositorioMascota.save(new Mascota("Toby", "Gato", 3, 4.0, "VIF", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/4g.png?raw=true"));
repositorioMascota.save(new Mascota("Oliver", "Gato", 6, 5.0, "Insuficiencia renal", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/5g.png?raw=true"));
repositorioMascota.save(new Mascota("Leo", "Gato", 4, 3.9, "Infección urinaria", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/6g.png?raw=true"));
repositorioMascota.save(new Mascota("Luna", "Gato", 3, 4.5, "Dermatitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/7g.png?raw=true"));
repositorioMascota.save(new Mascota("Salem", "Gato", 2, 4.3, "Otitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/8g.png?raw=true"));
repositorioMascota.save(new Mascota("Max", "Gato", 5, 4.1, "Toxoplasmosis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/9g.png?raw=true"));
repositorioMascota.save(new Mascota("Garfield", "Gato", 7, 5.5, "Obesidad", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/10g.png?raw=true"));
repositorioMascota.save(new Mascota("Tom", "Gato", 3, 4.0, "Rabia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/11g.png?raw=true"));
repositorioMascota.save(new Mascota("Rocky", "Gato", 6, 4.4, "Epilepsia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/12g.png?raw=true"));
repositorioMascota.save(new Mascota("Whiskers", "Gato", 5, 4.6, "Hipertiroidismo", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/13g.png?raw=true"));
repositorioMascota.save(new Mascota("Michi", "Gato", 2, 3.7, "Gastroenteritis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/14g.png?raw=true"));
repositorioMascota.save(new Mascota("Zeus", "Gato", 4, 4.2, "Moquillo", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/15g.png?raw=true"));
repositorioMascota.save(new Mascota("Sombra", "Gato", 3, 4.1, "Asma", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/16g.png?raw=true"));
repositorioMascota.save(new Mascota("Moka", "Gato", 2, 3.9, "Enfermedad renal", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/17g.png?raw=true"));
repositorioMascota.save(new Mascota("Chester", "Gato", 5, 4.8, "Diabetes", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/18g.png?raw=true"));
repositorioMascota.save(new Mascota("Rayo", "Gato", 3, 4.3, "Alergias", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/19g.png?raw=true"));
repositorioMascota.save(new Mascota("Otto", "Gato", 4, 4.5, "Conjuntivitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/20g.png?raw=true"));
repositorioMascota.save(new Mascota("Minnie", "Gato", 3, 4.2, "Enfermedad del tracto urinario", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/21g.png?raw=true"));
repositorioMascota.save(new Mascota("Nube", "Gato", 6, 4.6, "Insuficiencia hepática", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/22g.png?raw=true"));
repositorioMascota.save(new Mascota("Kira", "Gato", 4, 4.3, "Cáncer", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/23g.png?raw=true"));
repositorioMascota.save(new Mascota("Pelusa", "Gato", 5, 4.7, "Bronquitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/24g.png?raw=true"));
repositorioMascota.save(new Mascota("Snow", "Gato", 3, 4.0, "Sarna", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/25g.png?raw=true"));
    repositorioMascota.save(new Mascota("Nina", "Gato", 3, 4.2, "Anemia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/1g.png?raw=true"));
repositorioMascota.save(new Mascota("Cleo", "Gato", 2, 3.5, "Leucemia Felina", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/2g.png?raw=true"));
repositorioMascota.save(new Mascota("Chispa", "Gato", 4, 4.1, "Otitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/3g.png?raw=true"));
repositorioMascota.save(new Mascota("Misha", "Gato", 5, 4.8, "VIF", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/4g.png?raw=true"));
repositorioMascota.save(new Mascota("Tigre", "Gato", 3, 4.0, "Rabia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/5g.png?raw=true"));
repositorioMascota.save(new Mascota("Bola de Nieve", "Gato", 6, 5.1, "Parásitos", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/6g.png?raw=true"));
repositorioMascota.save(new Mascota("Bruno", "Gato", 4, 4.3, "Gingivitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/7g.png?raw=true"));
repositorioMascota.save(new Mascota("Coco", "Gato", 2, 3.6, "Sarna", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/8g.png?raw=true"));
repositorioMascota.save(new Mascota("Jazz", "Gato", 5, 4.7, "Asma Felina", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/9g.png?raw=true"));
repositorioMascota.save(new Mascota("Neko", "Gato", 3, 4.4, "Inmunodeficiencia Felina", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/10g.png?raw=true"));
repositorioMascota.save(new Mascota("Sami", "Gato", 4, 4.2, "Hipotiroidismo", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/11g.png?raw=true"));
repositorioMascota.save(new Mascota("Zoe", "Gato", 2, 3.9, "Moquillo", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/12g.png?raw=true"));
repositorioMascota.save(new Mascota("Gizmo", "Gato", 5, 4.9, "Dermatitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/13g.png?raw=true"));
repositorioMascota.save(new Mascota("Nina", "Gato", 3, 4.0, "Otitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/14g.png?raw=true"));
repositorioMascota.save(new Mascota("Toby", "Gato", 4, 4.5, "Cálculos Renales", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/15g.png?raw=true"));
repositorioMascota.save(new Mascota("Loki", "Gato", 3, 4.1, "Conjuntivitis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/16g.png?raw=true"));
repositorioMascota.save(new Mascota("Kiara", "Gato", 5, 4.6, "Obesidad", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/17g.png?raw=true"));
repositorioMascota.save(new Mascota("Mango", "Gato", 2, 3.7, "Gripe", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/18g.png?raw=true"));
repositorioMascota.save(new Mascota("Shadow", "Gato", 4, 4.3, "Epilepsia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/19g.png?raw=true"));
repositorioMascota.save(new Mascota("Azul", "Gato", 3, 4.0, "Gastroenteritis", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/20g.png?raw=true"));
repositorioMascota.save(new Mascota("Mochi", "Gato", 6, 5.0, "Insuficiencia Renal", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/21g.png?raw=true"));
repositorioMascota.save(new Mascota("Haru", "Gato", 4, 4.4, "Anemia Felina", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/22g.png?raw=true"));
repositorioMascota.save(new Mascota("Luna", "Gato", 3, 4.2, "Hipertiroidismo", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/23g.png?raw=true"));
repositorioMascota.save(new Mascota("Copito", "Gato", 5, 4.8, "Virus de la Rabia", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/24g.png?raw=true"));
repositorioMascota.save(new Mascota("Felina", "Gato", 4, 4.5, "Infección Urinaria", 
    "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/25g.png?raw=true"));










        repositorioCliente.save(new Cliente("18872435","Juan","juan@email.com",
    "3145194072"));
    repositorioCliente.save(new Cliente("19812305","Pedro","pedro@email.com",
    "3045591094"));
    repositorioCliente.save(new Cliente("27082133","Luis","luis@email.com",
    "3944193373"));
    repositorioCliente.save(new Cliente("17190115","Juliana","juli@email.com",
    "3305004013"));

    //50 dueños
    repositorioCliente.save(new Cliente("12345678","Carlos","carlos@email.com",
    "3101234567"));
    repositorioCliente.save(new Cliente("23456789","Andrés","andres@email.com",
    "3112345678"));
    repositorioCliente.save(new Cliente("34567890","María","maria@email.com",
    "3123456789"));
    repositorioCliente.save(new Cliente("45678901","Sofía","sofia@email.com",
    "3134567890"));
    repositorioCliente.save(new Cliente("56789012","Javier","javier@email.com",
    "3145678901"));
    repositorioCliente.save(new Cliente("67890123","Elena","elena@email.com",
    "3156789012"));
    repositorioCliente.save(new Cliente("78901234","Ricardo","ricardo@email.com",
    "3167890123"));
    repositorioCliente.save(new Cliente("89012345","Camila","camila@email.com",
    "3178901234"));
    repositorioCliente.save(new Cliente("90123456","Fernando","fernando@email.com",
    "3189012345"));
    repositorioCliente.save(new Cliente("01234567","Valentina","valentina@email.com",
    "3190123456"));
    repositorioCliente.save(new Cliente("11223344","Angela","angela@email.com",
    "3401122334"));
    repositorioCliente.save(new Cliente("22334455","Laura","laura@email.com",
    "3212233445"));
    repositorioCliente.save(new Cliente("33445566","Sebastián","sebastian@email.com",
    "3223344556"));
    repositorioCliente.save(new Cliente("44556677","Gabriela","gabriela@email.com",
    "3234455667"));
    repositorioCliente.save(new Cliente("55667788","Hernán","hernan@email.com",
    "3245566778"));
    repositorioCliente.save(new Cliente("66778899","Natalia","natalia@email.com",
    "3256677889"));
    repositorioCliente.save(new Cliente("77889900","Pablo","pablo@email.com",
    "3267788990"));
    repositorioCliente.save(new Cliente("88990011","Andrea","andrea@email.com",
    "3278899001"));
    repositorioCliente.save(new Cliente("99001122","Esteban","esteban@email.com",
    "3289900112"));
    repositorioCliente.save(new Cliente("00112233","Daniela","daniela@email.com",
    "3290011223"));
    
    // 20 nuevos dueños
    repositorioCliente.save(new Cliente("10101010","Cristina","cristina@email.com",
    "3601010101"));
    repositorioCliente.save(new Cliente("20202020","Federico","federico@email.com",
    "3612020202"));
    repositorioCliente.save(new Cliente("30303030","Vanessa","vanessa@email.com",
    "3623030303"));
    repositorioCliente.save(new Cliente("40404040","Mauricio","mauricio@email.com",
    "3634040404"));
    repositorioCliente.save(new Cliente("50505050","Paula","paula@email.com",
    "3645050505"));
    repositorioCliente.save(new Cliente("60606060","Rodrigo","rodrigo@email.com",
    "3656060606"));
    repositorioCliente.save(new Cliente("70707070","Diana","diana@email.com",
    "3667070707"));
    repositorioCliente.save(new Cliente("80808080","César","cesar@email.com",
    "3678080808"));
    repositorioCliente.save(new Cliente("90909090","Isabel","isabel@email.com",
    "3689090909"));
    repositorioCliente.save(new Cliente("11111111","Marcos","marcos@email.com",
    "3691111111"));
    repositorioCliente.save(new Cliente("12121212","Lorena","lorena@email.com",
    "3701212121"));
    repositorioCliente.save(new Cliente("13131313","Ernesto","ernesto@email.com",
    "3711313131"));
    repositorioCliente.save(new Cliente("14141414","Camilo","camilo@email.com",
    "3721414141"));
    repositorioCliente.save(new Cliente("15151515","Monica","monica@email.com",
    "3731515151"));
    repositorioCliente.save(new Cliente("16161616","Bruno","bruno@email.com",
    "3741616161"));
    repositorioCliente.save(new Cliente("17171717","Alejandra","alejandra@email.com",
    "3751717171"));
    repositorioCliente.save(new Cliente("18181818","Diego","diego@email.com",
    "3761818181"));
    repositorioCliente.save(new Cliente("19191919","Patricia","patricia@email.com",
    "3771919191"));
    repositorioCliente.save(new Cliente("20212121","Oscar","oscar@email.com",
    "3782021212"));
    repositorioCliente.save(new Cliente("21212121","Valeria","valeria@email.com",
    "3792121212"));
  

    // Asociar Mascota con Cliente
    // Cliente id 1 tiene mascota id 1 y 5
    Mascota mascotaAsociar = repositorioMascota.findById(1L).get();
    mascotaAsociar.setCliente(repositorioCliente.findById(1L).get());
    repositorioMascota.save(mascotaAsociar);

    mascotaAsociar = repositorioMascota.findById(5L).get();
    mascotaAsociar.setCliente(repositorioCliente.findById(1L).get());
    repositorioMascota.save(mascotaAsociar);

    // Cliente id 2 tiene mascota id 3
    mascotaAsociar = repositorioMascota.findById(3L).get();
    mascotaAsociar.setCliente(repositorioCliente.findById(2L).get());
    repositorioMascota.save(mascotaAsociar);

    // Cliente id 3 tiene mascota id 2
    mascotaAsociar = repositorioMascota.findById(2L).get();
    mascotaAsociar.setCliente(repositorioCliente.findById(3L).get());
    repositorioMascota.save(mascotaAsociar);

    // Cliente id 4 tiene mascota id 4
    mascotaAsociar = repositorioMascota.findById(4L).get();
    mascotaAsociar.setCliente(repositorioCliente.findById(4L).get());
    repositorioMascota.save(mascotaAsociar);
  }
}
