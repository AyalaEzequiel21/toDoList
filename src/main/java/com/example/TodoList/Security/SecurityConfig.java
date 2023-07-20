//package com.example.TodoList.Security;
//
//import com.example.TodoList.Security.Filters.JwtAthorizationFilter;
//import com.example.TodoList.Security.Filters.JwtAuthenticationFilter;
//import com.example.TodoList.Service.UserDetailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    JwtAthorizationFilter jwtAthorizationFilter;
//
//    @Autowired
//    UserDetailServiceImpl userDetailService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
//        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        jwtAuthenticationFilter.setFilterProcessesUrl("/login");     //ESTO SIRVE PARA MODIFICAR EL URL QUE VIENE X DEFECTO (/login)
//
//        return httpSecurity
//                .csrf(config -> config.disable())
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/users").permitAll();
////                    auth.requestMatchers("/auth/**").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .sessionManagement(session -> {
//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                })
//                .addFilter(jwtAuthenticationFilter)
//                .addFilterBefore(jwtAthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(passwordEncoder)
//                .and().build();
//    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("daleboca10"));
//    }
//}
