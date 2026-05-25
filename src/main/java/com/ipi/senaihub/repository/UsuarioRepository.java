package com.ipi.senaihub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipi.senaihub.model.Usuario;
import java.util.Optional;

// 1. Interface de repositório para a entidade Usuario, estende JpaRepository para fornecer operações CRUD e consultas personalizadas.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // 2. Método para encontrar um usuário pelo email, retorna um Optional para lidar com a possibilidade de não encontrar um usuário com o email fornecido.
}