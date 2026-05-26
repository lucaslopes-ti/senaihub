package com.ipi.senaihub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 1. Indica que esta é uma classe de configuração do Spring.
@Configuration
// 2. Desliga as configurações padrão de segurança do Spring Boot e permite que nós as definamos.
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    // 3. Este é o método principal que define QUAIS ROTAS são abertas e QUAIS são fechadas.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 4. Desabilitamos a proteção CSRF, pois em APIs REST Stateless nós usamos JWT.
                .csrf(csrf -> csrf.disable())
                // 5. Definimos a política de sessão como STATELESS (A API não guarda sessão/estado, o frontend guarda o JWT).
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 6. Começamos a ditar as regras de requisições:
                .authorizeHttpRequests(req -> {
                    // 7. Liberamos totalmente o Login e o Registro.
                    req.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll();
                    // 8. Liberamos o método GET de Cursos e Notícias para qualquer pessoa (Pública).
                    req.requestMatchers(HttpMethod.GET, "/api/cursos").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/noticias").permitAll();
                    
                    // 9. Apenas Administradores podem criar Cursos (POST).
                    req.requestMatchers(HttpMethod.POST, "/api/cursos").hasRole("ADMIN");
                    
                    // 10. Qualquer outra requisição precisará de autenticação (JWT Válido).
                    req.anyRequest().authenticated();
                })
                // 11. Dizemos para o Spring: "Rode o MEU filtro (SecurityFilter) ANTES do seu filtro padrão de Autenticação".
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // 12. Método para que possamos injetar o AuthenticationManager no nosso AuthController.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 13. Definimos qual é o algoritmo de Criptografia de Senhas. 
    // BCrypt é o padrão de mercado para criar hashes seguros (Nunca salvamos a senha pura no banco!).
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}