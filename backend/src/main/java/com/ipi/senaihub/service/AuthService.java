package com.ipi.senaihub.service;

import com.ipi.senaihub.model.Usuario;
import com.ipi.senaihub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 1. Ao implementar UserDetailsService, nós avisamos ao Spring Security que 
// esta é a classe responsável por buscar os dados do usuário no nosso Banco de Dados.
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    // 2. Método exigido pela interface do Spring Security.
    // O Spring Security manda um 'username' (no nosso caso, o E-mail) e nós retornamos um UserDetails.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 3. Buscamos nosso Usuario real no banco de dados.
        Usuario usuario = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail: " + username));

        // 4. Convertemos nosso Usuario (Entidade) para o UserDetails (Formato que o Spring Security entende).
        // Aqui dizemos ao Spring Security qual é o login, a senha criptografada que está no banco e a 'Role' (Autorização).
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // O Spring vai comparar essa hash com a senha enviada no Login
                .roles(usuario.getNivelAcesso().name().replace("ROLE_", "")) // Spring Security já adiciona 'ROLE_' internamente no hasRole()
                .build();
    }
    
}
