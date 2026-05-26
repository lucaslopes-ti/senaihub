package com.ipi.senaihub.controller;

import com.ipi.senaihub.model.Role;
import com.ipi.senaihub.model.Usuario;
import com.ipi.senaihub.repository.UsuarioRepository;
import com.ipi.senaihub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // 1. Usamos o AuthenticationManager para validar usuário e senha contra o banco.
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 2. Rota POST de Login. Recebemos e-mail e senha.
    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody Usuario dadosLogin) {
        // 3. Montamos o "Token de Username/Password" do Spring.
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosLogin.getEmail(), dadosLogin.getSenha());
        
        // 4. O manager dispara a validação (Que chama o nosso AuthService nos bastidores).
        var authentication = manager.authenticate(authenticationToken);

        // 5. Se chegou aqui, a senha estava certa! Vamos gerar o Token JWT JWT para ele.
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // 6. Retornamos o Token em formato texto/JSON para o React salvar no LocalStorage.
        return ResponseEntity.ok(tokenJWT);
    }

    // 7. Rota POST de Registro.
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody Usuario novoUsuario) {
        // 8. Verificamos se o email já existe.
        if (repository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já está em uso!");
        }

        // 9. Encriptamos a senha COM BCRYPT antes de salvar no banco!
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        
        // 10. Por padrão, todo novo registro será ALUNO.
        novoUsuario.setNivelAcesso(Role.ROLE_ALUNO);

        repository.save(novoUsuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
