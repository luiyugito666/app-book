package com.example.demo2.config;

import com.example.demo2.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

     @Autowired
     @Lazy
     private JwtReqFilter jwtReqFilter;



    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure ->{configure
                .requestMatchers(HttpMethod.GET,"/v1/book").hasRole("Empleado")
                .requestMatchers(HttpMethod.GET,"/v1/book/**").hasRole("Empleado")
                .requestMatchers(HttpMethod.POST ,"v1/book").hasRole("Jefe")
                .requestMatchers(HttpMethod.PUT,"v1/book/**").hasRole("Jefe")
                .requestMatchers(HttpMethod.DELETE,"//book/**").hasRole("Jefe")
             .requestMatchers(HttpMethod.GET,"/v1/category").hasRole("Empleado")
                    .requestMatchers(HttpMethod.GET,"/v1/category/**").hasRole("Empleado")
                    .requestMatchers(HttpMethod.POST ,"v1/category").hasRole("Jefe")
                    .requestMatchers(HttpMethod.PUT,"v1/category/**").hasRole("Jefe")
                    .requestMatchers(HttpMethod.DELETE,"//category/**").hasRole("Jefe")
                .requestMatchers("/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll();

        }).addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();


    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }

    /*  @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails jorge = User.builder()
                .username("jorge")
                .password("{noop}jorge123")
                .roles("Empleado")
                .build();
        UserDetails wini = User.builder()
                .username("wini")
                .password("{noop}wini123")
                .roles("Empleado", "Jefe")
                .build();
        UserDetails ken = User.builder()
                .username("ken")
                .password("{noop}ken123")
                .roles("Empleado","jefe")
                .build();

        return new InMemoryUserDetailsManager(jorge,wini,ken);
    }*/
}
