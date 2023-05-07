package com.onurergun.finobsbe.infrastructure.config.auth;

import com.onurergun.finobsbe.infrastructure.auth.AuthEntryPointJwt;
import com.onurergun.finobsbe.infrastructure.auth.JwtRequestFilter;
import com.onurergun.finobsbe.infrastructure.config.FinObsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer {

  private final FinObsProperties finObsProperties;

  private final JwtRequestFilter jwtRequestFilter;

  public SecurityConfigurer(FinObsProperties finObsProperties, JwtRequestFilter jwtRequestFilter) {
    this.finObsProperties = finObsProperties;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(new AuthEntryPointJwt())
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .antMatchers(HttpMethod.POST, finObsProperties.getApiPrefix() + "/users").permitAll()
        .antMatchers(HttpMethod.POST, finObsProperties.getApiPrefix() + "/auth/jwt").permitAll()
        .anyRequest().authenticated();

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
