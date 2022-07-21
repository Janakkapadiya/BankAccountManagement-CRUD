package com.accountmanagement.practice.ConfigurationAndSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
     @Autowired
     @Lazy
     private LoginFilter loginFilter;

     @Autowired
     private JwsFilter jwsFilter;
     //custom password storage

    @Bean
    public UserDetailsService userDetailsService()
    {
        var uds = new InMemoryUserDetailsManager();
        uds.createUser(User.builder().username("user").password("{noop}user").roles("USER").build());
        uds.createUser(User.builder().username("admin").password("{noop}admin").roles("ADMIN","USER").build());
        return uds;
    }

    //login filter for user and password authentication

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService)
    {
       var dao =  new DaoAuthenticationProvider();
       dao.setUserDetailsService(userDetailsService);
       return new ProviderManager(dao);
    }

    @Bean
    public SecurityFilterChain authFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.addFilterAt(loginFilter, BasicAuthenticationFilter.class);
        httpSecurity.addFilterAt(jwsFilter,BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }




}
