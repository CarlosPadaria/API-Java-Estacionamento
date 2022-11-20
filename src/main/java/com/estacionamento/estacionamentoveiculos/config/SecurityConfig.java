package com.estacionamento.estacionamentoveiculos.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123"))
                .roles("USERS")
                .and().passwordEncoder(passwordEncoder());
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs.yaml"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                // .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                //.antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/swagger-ui/index.html").permitAll()
                .antMatchers("/swagger-ui").permitAll()
                .antMatchers("/veiculos/{id}").permitAll()
                .antMatchers("/veiculos/{id}/calcularconta").permitAll()
                .antMatchers(HttpMethod.POST, "/veiculos").permitAll()
                .antMatchers(HttpMethod.GET, "/veiculos").permitAll()

                .anyRequest().authenticated()
                .and().httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}