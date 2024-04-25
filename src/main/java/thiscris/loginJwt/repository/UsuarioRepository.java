package thiscris.loginJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiscris.loginJwt.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Integer> {
    //encontrar por username
    Optional<Usuario> findByUsername(String username);
}
