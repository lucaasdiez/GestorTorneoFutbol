package com.gtf.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authProvider;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/api/**").permitAll()
//                        .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**", "/api/auth/**"/*"/api/auth/authenticate/**" */).permitAll()
//                        .requestMatchers("/api/arbitros/**").hasRole("ADMIN")
//                        .requestMatchers("api/equipos/equipo/{idEquipo}/eliminar/**").hasRole("ADMIN")
//                        .requestMatchers("/api/equipos/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/api/fechas/fecha/agregar/**").hasRole("ADMIN")
//                        .requestMatchers("/api/fechas/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/api/jugadores/jugador/agregar/**", "/api/jugadores/jugador/{idJugador}/eliminar/**","/api/jugadores/jugador/{dni}/estadisticas/agregar/**" ).hasRole("ADMIN")
//                        .requestMatchers("/api/jugadores/jugador/{dni}/estadistica/**", "/api/jugadores/jugador/equipo/{equipoNombre}/**", "/api/jugadores/jugador/{dni}/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/api/partidos/busqueda/**", "/api/partidos/partido/{idPartido}/eventos/**", "/api/partidos/partido/busqueda/**", "/api/partidos/partido/{id}/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/api/partidos/partido/agregar/**", "api/partidos/partido/eventoPartido/agregar/**", "api/partidos/partido/actualizar/**").hasRole("ADMIN")
//                        .requestMatchers("/api/torneos/torneo/crear/**").hasRole("ADMIN")
//                        .requestMatchers("/api/torneos/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("api/usuarios/registrar/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                       // .requestMatchers("/api/auth/register/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
