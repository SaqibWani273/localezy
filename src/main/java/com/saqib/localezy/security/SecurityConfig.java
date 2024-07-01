package com.saqib.localezy.security;

import com.saqib.localezy.service.CustomerDetailsService;
import com.saqib.localezy.service.jwt.JwtAuthFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Autowired
    private UserDetailsService CustomerDetailsService;
    @Autowired
    private JwtAuthFilterService jwtAuthFilterService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer
                ->configurer
                .requestMatchers(HttpMethod.POST, "/customer/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/customer/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/customer/verify-email").permitAll()
                .requestMatchers(HttpMethod.GET,"customer/me").hasRole("CUSTOMER")
                .anyRequest().hasRole("ADMIN") )
                //add jwt filter before security filter to authenticate users
                //using jwt token
                .addFilterBefore(jwtAuthFilterService, UsernamePasswordAuthenticationFilter.class);;
        http.csrf(csrf->csrf.disable());
        //use basic authentication
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //for user authentication
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    private UserDetailsService userDetailsService() {
        return CustomerDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return  new ProviderManager(authenticationProvider());
    }


}
