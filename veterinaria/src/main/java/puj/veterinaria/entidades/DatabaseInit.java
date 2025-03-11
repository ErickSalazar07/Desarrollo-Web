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

    repositorioCliente.save(new Cliente("18872435","Juan","juan@email.com",
    "3145194072"));
    repositorioCliente.save(new Cliente("19812305","Pedro","pedro@email.com",
    "3045591094"));
    repositorioCliente.save(new Cliente("27082133","Luis","luis@email.com",
    "3944193373"));
    repositorioCliente.save(new Cliente("17190115","Juliana","juli@email.com",
    "3305004013"));


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
