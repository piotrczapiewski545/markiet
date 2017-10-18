package pl.gda.wsb.markiet.db;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Piotr Czapiewski
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByLogin(String login);
    
}
