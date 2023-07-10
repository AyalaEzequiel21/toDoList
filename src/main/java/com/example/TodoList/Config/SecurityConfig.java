package com.example.TodoList.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {      // configuramos que url debera autenticarse
                    auth.requestMatchers("/toDoList/V1/users").permitAll();     // de esta manera indicamos una url especifica a la cual no se le pedira autenticacions
                    auth.anyRequest().authenticated();   // Y aca indicamos que todas las demas url que no esten indicadas deberan ser autenticadas
                })
                .formLogin(form -> {
                    form.successHandler(successHandler());  // una vez iniciada la sesion sera redirigido a la url
                    form.permitAll();// esto es para indicar que al login se le permite el acceso.
                })
                .sessionManagement(man -> {
                    man.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);   // aca indicamos que se creara una sesion siempre que se inicie sesion.
                    man.invalidSessionUrl("/login");   // si la autenticacion falla nos redirigira al login
                    man.maximumSessions(2);     // Numero maximo de sesiones permitidas
                    man.sessionFixation().migrateSession();    // ante un ataque de fijacion, genera un nuevo id como metodo de seguridad
                });
        return httpSecurity.build();
    }
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/toDoList/V1/users");
        });
    }
}
