package com.accountmanagement.practice.ConfigurationAndSecurity;

import com.accountmanagement.practice.Services.CustomAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

    public static final String[] ALLOWED_URLS = {
            "/h2-console/**",
            "/authenticate",
            "/saveUser",
            "/v3/api-docs",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/upload",
            "/ApiData/**",
            "/media/{fileName}"
     };
     @Autowired
     private JwtAuthenticationEntrypoint authenticationEntrypoint;

     @Autowired
     @Qualifier("userDetails")
     private UserDetailsService userDetailsService;

     @Autowired
     private JwtFilter jwtFilter;

     @Autowired
     private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().
                 authenticationEntryPoint(this.authenticationEntrypoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(ALLOWED_URLS);
    }
    @Bean
    public AuthenticationManager configureAuthenticationManager() {
        return CustomAuthenticationManager.builder().userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).build();
    }

}