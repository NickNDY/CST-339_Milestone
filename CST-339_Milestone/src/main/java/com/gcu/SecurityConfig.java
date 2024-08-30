package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gcu.service.LoginService;



/**
 * Security configuration class for the application.
 * Configures authentication and authorization settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginService loginService;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * Configures the security filter chain, defining which requests require authentication and
     * specifying login, logout, and basic HTTP authentication settings.
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the {@link SecurityFilterChain} object
     * @throws Exception if an error occurs while configuring the security chain
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/library/**", "/service/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
        				.usernameParameter("username")
        				.passwordParameter("password")
                        .permitAll()
        				.defaultSuccessUrl("/library", true) // Define URL to navigate to upon successful login
                )
        		.logout(logout -> logout
        				.logoutUrl("/logout") // Define logout path
        				.invalidateHttpSession(true)
        				.clearAuthentication(true)
        				.permitAll() // Permit access to logout page
        				.logoutSuccessUrl("/") // Define URL to navigate to after logout
        		)
        		.httpBasic(withDefaults()); // Enable basic HTTP authentication with defaults

        return http.build();
    }


    /**
     * Configures the {@link AuthenticationManager} to use the {@link LoginService} for user authentication
     * and the {@link PasswordEncoder} for password encoding.
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the configured {@link AuthenticationManager} object
     * @throws Exception if an error occurs while configuring the authentication manager
     */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(loginService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }


    /**
     * Creates a {@link PasswordEncoder} bean that uses the {@link BCryptPasswordEncoder}.
     *
     * @return the {@link PasswordEncoder} bean
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
}
