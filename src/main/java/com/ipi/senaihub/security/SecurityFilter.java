package com.ipi.senaihub.security;

import com.ipi.senaihub.model.Usuario;
import com.ipi.senaihub.repository.UsuarioRepository;
import com.ipi.senaihub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// 1. @Component transforma essa classe num Bean gerenciado pelo Spring.
@Component
// 2. Herdamos OncePerRequestFilter para garantir que este filtro execute UMA VEZ em cada requisição que chegar na API.
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 3. Capturamos o cabeçalho "Authorization" da requisição HTTP (onde o Frontend React enviará o Token).
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // 4. Se existir token, mandamos nosso TokenService validá-lo. Retorna o e-mail se for válido.
            var subject = tokenService.validarTokenEObterSubject(tokenJWT);
            
            if (!subject.isEmpty()) {
                // 5. Buscamos o usuário no banco para pegar as Roles atualizadas.
                Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(subject);
                
                if(usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();
                    
                    // 6. Criamos a lista de permissões do usuário para o Spring Security.
                    var authorities = List.of(new SimpleGrantedAuthority(usuario.getNivelAcesso().name()));
                    
                    // 7. Forçamos a autenticação do usuário no contexto atual do Spring Security.
                    // Isso avisa ao sistema: "Este cara está logado e estes são os privilégios dele".
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // 8. Segue o fluxo. Se o cara não mandou token, ele continuará como "não autenticado" (Anônimo).
        filterChain.doFilter(request, response);
    }

    // 9. Extrai o token removendo o prefixo "Bearer ".
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}