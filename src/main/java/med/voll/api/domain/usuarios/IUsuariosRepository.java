package med.voll.api.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuariosRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String username);
}