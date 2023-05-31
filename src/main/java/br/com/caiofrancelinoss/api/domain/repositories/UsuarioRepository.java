package br.com.caiofrancelinoss.api.domain.repositories;

import br.com.caiofrancelinoss.api.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
