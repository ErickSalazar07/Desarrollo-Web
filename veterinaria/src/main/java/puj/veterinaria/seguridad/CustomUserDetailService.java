package puj.veterinaria.seguridad;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import puj.veterinaria.entidades.Administrador;
import puj.veterinaria.entidades.Cliente;
import puj.veterinaria.entidades.Role;
import puj.veterinaria.entidades.UserEntity;
import puj.veterinaria.entidades.Veterinario;
import puj.veterinaria.repositorios.RepositorioRole;
import puj.veterinaria.repositorios.RepositorioUserEntity;

@Service
public class CustomUserDetailService implements UserDetailsService {

    //Se debe hacer un mpaeo de userEntity a userdatail(user estandar que usa spring securty)

    @Autowired
    private RepositorioUserEntity userRepository;

    @Autowired
    private RepositorioRole roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Aquí sacamos un usuario de nuestra bd y lo convertimos en un userDetail(esta es una interfaz, su implementación se llama User)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );
        //Necesitamos hacer un mapeo entre el rol de la bd a un GrantedAuthority
        UserDetails userDetails = new User (userDB.getUsername(),
         userDB.getPassword(),
          mapToGrantedAuthorities(userDB.getRoles()));

          return userDetails;
    }

    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    //metodos para pasar delas entidades a userentity y guaradr en base de datos

    public UserEntity clienteToUser(Cliente cliente){
        UserEntity user = new UserEntity();
        user.setUsername(cliente.getCorreo());
        user.setPassword(passwordEncoder.encode(cliente.getCedula()));

        Role roles = roleRepository.findByName("CLIENTE").get();
        user.setRoles(List.of(roles));
        return user;
    }

    public UserEntity veterinarioToUser(Veterinario veterinario){
        UserEntity user = new UserEntity();
        user.setUsername(veterinario.getCedula());
        user.setPassword(passwordEncoder.encode(veterinario.getContrasena()));
        
        Role roles = roleRepository.findByName("VETERINARIO").get();
        user.setRoles(List.of(roles));
        return user;
    }

    public UserEntity AdminToUser(Administrador admin){
        UserEntity user = new UserEntity();
        user.setUsername(admin.getUsername());
        user.setPassword(passwordEncoder.encode(admin.getCelular()));
        
        Role roles = roleRepository.findByName("ADMINISTRADOR").get();
        user.setRoles(List.of(roles));
        return user;
    }
    
}
