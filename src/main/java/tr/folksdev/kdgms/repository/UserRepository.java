package tr.folksdev.kdgms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.folksdev.kdgms.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

}
