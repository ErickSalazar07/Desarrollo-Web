package puj.veterinaria.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtAuthEntryPoint jwtAuthEntryPoint;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    /*
     * Se recomienda desactivar CSRF cuando se la comunicación se están manejando
     * páginas web
     * donde la comunicación entre la página y el servidor es mediante peticiones
     * HTTP
     */
    http.csrf(AbstractHttpConfigurer::disable)
      /* H2 */
      .headers(headers -> headers.frameOptions(frame -> frame.disable()))
      .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(requests -> requests
        /* H2 */
//-------------------------------------- SECCION .permitAll() -------------------------------------------
        .requestMatchers("/h2/**").permitAll()
        .requestMatchers("/cliente/login").permitAll()
        .requestMatchers("/veterinario/login").permitAll()
        .requestMatchers("/admin/login").permitAll()
//-------------------------------------- END SECCION .permitAll() ---------------------------------------
//-------------------------------------- SECCION .hasAutority() -------------------------------------------
        .requestMatchers("/admin/**").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/get-veterinario**").hasAuthority("VETERINARIO")
        .requestMatchers("/veterinario/veterinario_autenticado").hasAuthority("VETERINARIO")
        .requestMatchers("/veterinario/details").hasAuthority("VETERINARIO")
        .requestMatchers("/veterinario/add").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/veterinarios").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/get-num-veterinarios-activos").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/get-num-veterinarios-inactivos").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/update**").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/veterinario/delete/**").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/droga/drogas").hasAnyAuthority("VETERINARIO","ADMINISTRADOR")
        .requestMatchers("/droga/get-droga/**").hasAuthority("VETERINARIO")
        .requestMatchers("/droga/get-total**").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/mascota/get-num-mascotas**").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/mascota/add").hasAuthority("VETERINARIO")
        .requestMatchers("/mascota/mascotas").hasAuthority("VETERINARIO")
        .requestMatchers("/mascota/cambiar-estado/**").hasAuthority("VETERINARIO")
        .requestMatchers("/mascota/update/**").hasAuthority("VETERINARIO")
        .requestMatchers("/mascota/delete/**").hasAuthority("VETERINARIO")
        .requestMatchers("/tratamiento/get-num-tratamientos-ultimo-mes").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/tratamiento/get-num-tratamientos-mas-unidad-vendida").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/tratamiento/get-top3-tratamientos-mas-unidad-vendida").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/tratamiento/add").hasAuthority("VETERINARIO")
        .requestMatchers("/cliente/get-cliente**").hasAuthority("CLIENTE")
        .requestMatchers("/cliente/details").hasAuthority("CLIENTE")
        .requestMatchers("/cliente/cliente_autenticado").hasAuthority("CLIENTE")
        .requestMatchers("/cliente/add").hasAuthority("VETERINARIO")
        .requestMatchers("/cliente/update/**").hasAuthority("VETERINARIO")
        .requestMatchers("/cliente/delete/**").hasAuthority("VETERINARIO")
        .requestMatchers("/cliente/clientes").hasAuthority("VETERINARIO")
        .requestMatchers("/cliente/clientes").hasAuthority("ADMINISTRADOR")
        .requestMatchers("/admin/**").hasAuthority("ADMINISTRADOR")
//-------------------------------------- END SECCION .hasAutority() ---------------------------------------
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .anyRequest().permitAll())
      .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));

    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /*
   * Permite autenticar a los usuarios con usuario y contrasena
   * Al autenticar devuelve un onjeto Authentication que posteriormente se puede
   * usar a traves de SecurityContextHolder
   * para obtener el usuario autenticado
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public JWTAuthenticationFilter jwtAuthenticationFilter() {
    return new JWTAuthenticationFilter();
  }

}