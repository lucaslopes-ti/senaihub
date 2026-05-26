package com.ipi.senaihub.service;

import com.ipi.senaihub.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// 1. Serviço especializado em gerar e validar o JSON Web Token (JWT).
@Service
public class TokenService {

    // 2. Lemos a variável lá do application.properties.
    @Value("${api.security.token.secret}")
    private String secret;

    // 3. Método para gerar o token quando o usuário fizer Login.
    public String gerarToken(Usuario usuario) {
        try {
            // 4. O algoritmo HMAC256 usa nossa chave secreta para "assinar" o token. 
            // Ninguém consegue forjar esse token sem ter a nossa chave.
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            
            return JWT.create()
                    .withIssuer("portal-senai-api") // Quem emitiu o token
                    .withSubject(usuario.getEmail()) // Qual é o "assunto" principal (geralmente o email/login do usuário)
                    .withClaim("role", usuario.getNivelAcesso().name()) // Guardamos o perfil dentro do token!
                    .withExpiresAt(gerarDataExpiracao()) // Data de validade (ex: 2 horas)
                    .sign(algoritmo); // Assina e gera a String final
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    // 5. Método que o Filtro de Segurança chamará a cada requisição para saber se o Token é válido.
    public String validarTokenEObterSubject(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("portal-senai-api")
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o email guardado no token
        } catch (JWTVerificationException exception) {
            // Se o token for falso, expirado ou alterado, cairá aqui e retornará vazio.
            return "";
        }
    }

    // 6. Define que o token expira em 2 horas a partir da geração.
    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}