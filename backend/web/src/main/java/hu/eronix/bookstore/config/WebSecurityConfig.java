package hu.eronix.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends hu.eronix.bookstore.auth.security.config.WebSecurityConfig {
}

